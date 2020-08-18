package com.test.dao;

import com.test.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/8/17 19:49
 * @package com.test.dao
 */
@Mapper
public interface LoginDao {
    int save(User user);

    User selectUsername(String username);

    User selectByUsernameAndPassword(User user);
}
