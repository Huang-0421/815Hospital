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

@TableName("department")
//科室
public class Department {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "location")
    private String location;
}
