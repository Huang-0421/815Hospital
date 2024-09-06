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

@TableName("feedback")
public class Feedback {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "patient_name")
    private String patientName;

    @TableField(value = "create_time")
    private String createTime;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "type")
    private String type;

    @TableField(value = "content")
    private String content;

    @TableField(value = "image")
    private String image;

    @TableField(value = "patient_id")
    private Long patientId;
}
