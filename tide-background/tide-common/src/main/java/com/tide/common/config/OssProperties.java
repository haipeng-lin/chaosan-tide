package com.tide.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/11/21 19:04
 * @Description OSS上传文件
 */

@Component
@Data
@ConfigurationProperties(prefix = "file.oss")
public class OssProperties {

    /**
     * 上传文件前缀路径
     */
    private String prefix;
    /**
     * oss类型
     */
    private String type;

    /**
     * 下面几个是oss的配置参数
     */
    private String endpoint;
    private String ak;
    private String sk;
    private String bucket;
    private String host;
}
