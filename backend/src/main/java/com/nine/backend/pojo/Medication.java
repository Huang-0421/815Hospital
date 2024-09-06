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

@TableName("medication")
public class Medication {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "price")
    private Integer price;

    @TableField(value = "stock")
    private Integer stock;

    @TableField(value = "image")
    private String image;
}
