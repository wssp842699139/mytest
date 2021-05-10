package com.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/27 23:38
 * @package com.test.config
 */
@Configuration
@MapperScan(basePackages = "com.test.dao", sqlSessionFactoryRef = "test1SqlSessionFactory")
public class MybatisConfigSimple {
   // @Value(value = "${mybatis.mapper-locations}")
    private String mappersLocations = "classpath:com/test/dao/*.xml";

    @Bean(name = "test1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource") //读取的数据源前缀, 跟yml文件对应
    public DataSource DB1dataSource(){
        return new DruidDataSource();
    }


    @Primary
    @Bean(name = "test1SqlSessionFactory")
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(pathMatchingResourcePatternResolver.getResources(mappersLocations));
        sqlSessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactory.getObject();
    }

    //mybaits连接配置数据源 简单执行器

    @Bean(name = "testSqlSessionTemplateSimple")
    public SqlSessionTemplate testSqlSessionTemplateSimple(@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.REUSE);
    }


}
