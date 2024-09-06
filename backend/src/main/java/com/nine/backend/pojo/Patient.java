package com.nine.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @define 用户类
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/19 下午2:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

@TableName("patient")
public class Patient {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(value = "name")
    private String username;
    @TableField(value = "gender")
    private Integer gender;//1代表男，0代表女
    @TableField(value = "phone")
    private String phone;
    @TableField(value = "password")
    private String password;
    @TableField(value = "id_card")
    private String idCard;
    @TableField(value = "photo")
    private String photo = "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/e7195fae-ca61-449f-8c47-7bfe364c6a6b.png";
    @TableField(value = "address")
    private String address;
    @TableField(value = "message_code")
    private String messageCode; //用于记录注册时的验证码
    @TableField(value = "registration_id")
    private Long registrationId;  //挂号单id
}
