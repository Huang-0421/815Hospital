package com.nine.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@TableName("admin")
public class Admin {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;//123456
}
