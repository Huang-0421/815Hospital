package com.nine.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@TableName("registration")
public class Registration {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "doctor_name")
    private String doctorName;

    @TableField(value = "patient_name")
    private String patientName;

    @TableField(value = "doctor_id")
    private Long doctorId;

    @TableField(value = "patient_id")
    private Long patientId;

    @TableField(value = "fee")
    private Integer fee;

    @TableField(value = "inquiry_time")
    private String inquiryTime;

    @TableField(value = "department")
    private String department;

    @TableField(value = "flag")
    private int flag = 0;

}
