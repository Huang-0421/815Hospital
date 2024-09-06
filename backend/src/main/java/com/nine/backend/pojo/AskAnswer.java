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
@TableName("askanswer")
public class AskAnswer {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "patient_id")
    private Long patientId;

    @TableField(value = "doctor_id")
    private Long doctorId;

    @TableField(value = "question")
    private String question;

    @TableField(value = "answer")
    private String answer;

}