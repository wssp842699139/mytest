package com.test;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/8/1 22:58
 * @package com.test
 */
@Mapper
public interface TestJenkinsDao {
    List<User> selectUser(User user);

    int updateUser(User user);

    int insertUser(User insertUser);

    List<User> selectUserByUsername(User user);
}
