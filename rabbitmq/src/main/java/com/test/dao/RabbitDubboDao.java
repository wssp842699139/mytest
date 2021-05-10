package com.test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/29 8:03
 * @package com.test.dubboTest
 */
@Mapper
public interface RabbitDubboDao {

    @Update("update stock set number = 90 where id = 1")
     void testUpdate();
}
