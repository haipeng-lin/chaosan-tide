package com.tide.common.service.impl;

import com.tide.common.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/11/21 19:27
 * @Description 本地存储类
 */

@Service
@Slf4j
@ConditionalOnExpression(value = "#{'local'.equals(environment.getProperty('file.type'))}")
public class LocalStorageServiceImpl implements FileService {

    @Override
    public String upload(InputStream input, String fileType) {
        return "";
    }
}
