package com.nine.backend;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;
import java.util.Collections;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/18 上午10:16
 */
public class AutoGenerator {
    public static void main(String[] args) {
        //连接数据库
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false", "root", "20040421a")
                .globalConfig(builder -> builder
                        .author("huangRuijie")//作者
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")//输出目录
                        .commentDate("yyyy-MM-dd")
                )
                .packageConfig(builder -> builder
                        .parent("com.huang")
                        .moduleName("mybatisplusprc")
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, "src/main/resources/mapper"))//配置xml文件路径
                )
                .strategyConfig(builder -> builder
                        .addInclude("t_user") // 这是要包含的表名,可以addInclude多个表
                        .addTablePrefix("t_", "c_") //如果表名中包含这些前缀，它们将被自动移除，生成的类名将不包括这些前缀。
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
