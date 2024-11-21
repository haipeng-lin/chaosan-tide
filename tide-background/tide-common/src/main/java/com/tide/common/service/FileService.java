package com.tide.common.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

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
     * @param input 文件输入流
     * @param fileType 文件类型
     * @return 文件路径
     */
    String upload(InputStream input, String fileType);


    /**
     * 获取文件类型
     *
     * @param input
     * @param fileType
     * @return
     */
    default String getFileType(ByteArrayInputStream input, String fileType) {
        if (StringUtils.isNotBlank(fileType)) {
            // 文件类型不为空
            return fileType;
        }
        // TODO 获取文件类型
        return null;
    }
}
