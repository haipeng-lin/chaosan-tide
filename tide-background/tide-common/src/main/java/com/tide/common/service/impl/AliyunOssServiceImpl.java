package com.tide.common.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.tide.common.config.OssProperties;
import com.tide.common.service.FileService;
import com.tide.common.utils.Md5Util;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/11/21 19:18
 * @Description Oss上传文件实现
 */

@Slf4j
@Component
@ConditionalOnExpression(value = "#{'aliyun'.equals(environment.getProperty('file.type'))}")
public class AliyunOssServiceImpl implements FileService {

    private static final int SUCCESS_CODE = 200;

    @Autowired
    private OssProperties ossProperties;

    @Autowired
    private OSS ossClient;

    /**
     * 上传文件
     * @param input 文件输入流
     * @param fileType 文件类型
     * @return 文件路径
     */
    @Override
    public String upload(InputStream input, String fileType) {
        try {
            // 转换成字节
            byte[] bytes = StreamUtils.copyToByteArray(input);
            return upload(bytes, fileType);
        } catch (OSSException oe) {
            log.error("Oss上传文件失败，msg:{}, code:{}, reqId:{}, host:{}", oe.getErrorMessage(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());
            return "";
        } catch (Exception ce) {
            log.error("Oss上传文件失败，msg:{}, code:{}, reqId:{}, host:{}", ce.getMessage());
            return "";
        }
    }

    /**
     * 上传文件
     * @param bytes 文件字节数组
     * @param fileType 文件类型
     * @return 文件路径
     */
    public String upload(byte[] bytes, String fileType) {
        try {
            // 1、计算md5作为文件名
            log.info("文件md5值：{}",Md5Util.encode(bytes));
            String fileName = Md5Util.encode(bytes);
            ByteArrayInputStream input = new ByteArrayInputStream(bytes);

            // 2、根据日期构建文件的 OSS 存储路径
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String datePath = sdf.format(new Date());
            String ossPath = datePath + "/" + fileName + getFileType(input, fileType);

            // 3、创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossProperties.getBucket(), fileName, input);
            // 设置该属性可以返回response。如果不设置，则返回的response为空。
            putObjectRequest.setProcess("true");

            // 4、上传文件
            PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
            if (SUCCESS_CODE == putObjectResult.getResponse().getStatusCode()) {
                // 返回文件路径
                return ossProperties.getHost() + fileName;
            } else {
                log.error("upload to oss error! response:{}", putObjectResult.getResponse().getStatusCode());
                // Guava 不允许回传 null
                return "";
            }
        } catch (OSSException oe) {
            log.error("Oss上传文件失败，msg:{}, code:{}, reqId:{}, host:{}", oe.getErrorMessage(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());
            return  "";
        } catch (Exception ce) {
            log.error("Oss上传文件失败，msg:{}, code:{}, reqId:{}, host:{}", ce.getMessage());
            return  "";
        }
    }
}
