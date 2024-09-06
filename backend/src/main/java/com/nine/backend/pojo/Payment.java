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

@TableName("payment")
public class Payment {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "diagnosis")
    private String diagnosis;

    @TableField(value = "medication_name")
    private String medicationName;

    @TableField(value = "quantity")
    private Integer quantity;


    @TableField(value = "amount_due")
    private Integer amountDue;

    @TableField(value = "patient_id")
    private Long patientId;

    @TableField(value = "doctor_id")
    private Long doctorId;

    @TableField(value = "patient_name")
    private String patientName;

    @TableField(value = "price")
    private Integer price;

    @TableField(value = "flag")
    private Integer flag = 0;//默认未缴费
}
