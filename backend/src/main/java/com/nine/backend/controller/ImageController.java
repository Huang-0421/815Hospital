package com.nine.backend.controller;

import com.nine.backend.utils.AliOSSUtils;
import com.nine.backend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/25 下午3:05
 */
@RestController
public class ImageController {
        @Autowired
        private AliOSSUtils aliOSSUtils;
        @PostMapping("/upload")
        public Result upload(MultipartFile image) throws IOException {
            String url = aliOSSUtils.upload(image);
            return Result.success(url);
        }
}
