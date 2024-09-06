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

@TableName("sms")
public class Sms {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "patient_id")
    private Integer patientId;

    @TableField(value = "registration_sms")
    private String registrationSms;

    @TableField(value = "reminder_sms")
    private String reminderSms;
}

