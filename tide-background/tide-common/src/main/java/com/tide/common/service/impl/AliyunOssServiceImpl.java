package com.tide.common.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.tide.common.config.OssProperties;
import com.tide.common.service.FileService;
import com.tide.common.utils.Md5Util;
import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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

    /**
     * 上传文件
     * @param multipartFile 文件
     * @return 文件路径
     *
     * 注意：一个InputStream只能使用一次，多次使用同一个InputStream，会出现上传到OSS的图片为0kb
     * 解决方案：将InputStream转成字节数组，根据字节数组生成md5值，最后将字节数组转成InputStream
     */
    @Override
    public String upload(MultipartFile multipartFile) {

        InputStream inputStream =null;

        try {
            // 1、创建OSS对象
            OSS ossClient = new OSSClientBuilder().build(ossProperties.getEndpoint(), ossProperties.getAk(), ossProperties.getSk());

            // 2、计算md5作为文件名
            inputStream = multipartFile.getInputStream();
            byte[] bytes = StreamUtils.copyToByteArray(inputStream);

            String fileName = Md5Util.encode(bytes);

            // 3、根据日期构建文件的 OSS 存储路径
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
            String datePath = sdf.format(new Date());

            // 获取后缀
            String originalFilename = multipartFile.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            log.info("文件后缀为：{}",extension);
            String ossPath = datePath + "/" + fileName + extension;

            // 4、上传文件
            PutObjectResult result = ossClient.putObject(ossProperties.getBucket(),ossPath,new ByteArrayInputStream(bytes));
            if(result!=null){
                // 返回文件路径
                return ossProperties.getHost() + ossPath;
            } else {
                log.error("upload to oss error! response:{}", result.getResponse().getStatusCode());
                return "";
            }
        } catch (OSSException oe) {
            log.error("Oss上传文件失败，msg:{}, code:{}, reqId:{}, host:{}", oe.getErrorMessage(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());
            return  "";
        } catch (Exception ce) {
            log.error("Oss上传文件失败，msg:{}, code:{}, reqId:{}, host:{}", ce.getMessage());
            return  "";
        }finally {
            if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        }
    }

}
