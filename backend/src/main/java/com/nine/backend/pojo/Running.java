package com.nine.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/28 上午3:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("running")
public class Running {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String date;
    private String sort;
    private String name;
    private String type;
    private int quantity;
    private int price;
    private int total;
    private String status;
    private String payway;
    private String operateman;
    private String description;
    private Long department;
}
