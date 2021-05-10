package com.test.设计模式等;

import com.test.bean.Student;
import com.test.设计模式等.设计模式.observe.Observe1;
import com.test.设计模式等.设计模式.observe.Observeable;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * @version V1.0
 * @Author ship
 * @Date 2020/7/6 19:45
 * @package com.test.excel
 */
public class Test2 {
    private  Student student;


    public static void main(String[] args) throws IOException {
        Observeable observeable = new Observeable();
        observeable.addObserver(new Observe1());
        observeable.testOb();
    }


    public static void test3() throws SQLException {
        List<Student> a=  new ArrayList<>();
        Collections.sort(a, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
               return o1.getAge()-o2.getAge();
            }
        });
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(new Configuration());
        SqlSession sqlSession = build.openSession();
        Connection connection = sqlSession.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("aa");
        ResultSet resultSet = statement.executeQuery();
        sqlSession.commit();
    }
}
