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

@TableName("ward")
public class Ward {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "room_number")
    private String roomNumber;

    @TableField(value = "patient_id")
    private Long patientId; // 0 for Available, 1 for Occupied
    @TableField(value = "department_name")
    private String departmentName;
}
