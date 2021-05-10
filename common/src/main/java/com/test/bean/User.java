package com.test.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/8/1 23:00
 * @package com.test
 */
@Data
public class User implements Serializable {

    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("账号")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("版本号")
    private int version;

}
