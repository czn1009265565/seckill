package com.czndata.seckill.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user_info", autoResultMap = true)
public class UserInfoDO {
    @TableId
    private Long userId;
    private String name;
    private String password;
    private Integer gender;
    private Integer age;
    private String telphone;
    private String registerMode;
    private String thirdPartyId;
}
