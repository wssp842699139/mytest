package com.test.Utils;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/28 0:11
 * @package com.test.Utils
 */
public class SqlSessionUtil {
    @Autowired
    SqlSessionFactory testSqlSessionFactory;

    public SqlSessionTemplate openSession(ExecutorType type,Boolean isCash){
        System.out.println(testSqlSessionFactory);
        return new SqlSessionTemplate(testSqlSessionFactory, type);
    }
}
