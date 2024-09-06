package com.nine.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/20 下午2:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("doctor")
public class Doctor {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "gender")
    private Integer gender;
    @TableField(value = "age")
    private Integer age;
    @TableField(value = "phone")
    private String phone;
    @TableField(value = "password")
    private String password;
    @TableField(value = "degree")
    private String degree;//学位
    @TableField(value = "title")
    private String title;//职位
    @TableField(value = "department_id")
    private Long departmentId;//职位
    @TableField(value = "biography")
    private String biography;//简介
    @TableField(value = "fee")
    private Integer fee;
    @TableField(value = "duty")
    private Integer duty;
    @TableField(value = "image")
    private String image = "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/e7195fae-ca61-449f-8c47-7bfe364c6a6b.png";
}
