package com.test.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/28 2:50
 * @package com.test.config
 */

@Slf4j
@Component
@Intercepts(
        {@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
//       @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
public class SqlInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //System.out.println(invocation.toString());
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameterObject = args[1];
        String sqlCommandType = ms.getSqlCommandType().toString();
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        String origSql = boundSql.getSql();
        if (SqlCommandType.INSERT.toString().equals(sqlCommandType)){
            if(origSql.startsWith("/*BULK*/")){
                Configuration configuration = ms.getConfiguration();
                configuration.setDefaultExecutorType(ExecutorType.REUSE);
                // 重新new一个查询语句对象
                BoundSql newBoundSql = new BoundSql(configuration, origSql,
                        boundSql.getParameterMappings(), boundSql.getParameterObject());

                // 把新的查询放到statement里
                MappedStatement newMs = newMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
                for (ParameterMapping mapping : boundSql.getParameterMappings()) {
                    String prop = mapping.getProperty();
                    if (boundSql.hasAdditionalParameter(prop)) {
                        newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
                    }
                }
                args[0] = newMs;
                return invocation.proceed();
            }
        }
        return invocation.proceed();
    }

    /**
     * 定义一个内部辅助类，作用是包装 SQL
     */
    class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }

    }

    private MappedStatement newMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new
                MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
            builder.keyProperty(ms.getKeyProperties()[0]);
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
