package com.nine.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/25 下午12:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "doctor_id")
    private Long doctorId;
    @TableField(value = "content")
    private String content;
}
