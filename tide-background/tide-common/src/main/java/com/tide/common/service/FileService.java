package com.tide.common.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/11/21 19:25
 * @Description 文件类
 */

public interface FileService {


    /**
     * 文件上传
     *
     * @param multipartFile 文件
=    * @return 文件路径
     */
    String upload(MultipartFile multipartFile) throws IOException;


}
