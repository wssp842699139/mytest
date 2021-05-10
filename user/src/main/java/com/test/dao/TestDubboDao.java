package com.test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/29 7:53
 * @package com.test.dao
 */
@Mapper
public interface TestDubboDao {

    @Update("update account set money = money -100 where id = 1")
     void testUpdate();
}
