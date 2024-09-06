package com.nine.backend.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/26 上午10:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "ward_info")
public class WardInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer history;
    private Integer remaining;
}
