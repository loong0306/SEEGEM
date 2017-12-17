package me.dragon.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * <p>Description 阿里云oss文件服务器配置文件 </p>
 */
@Data
@Configuration("ossConfigure")
@ConfigurationProperties(prefix = OSSConfigure.OSS_CONF)
public class OSSConfigure {
    public final static String OSS_CONF = "oss_conf";

    /**
     * OSS 访问域名
     */
    private String endpoint;

    /**
     * 外网访问域名
     */
    private String outEndpoint;
    /**
     * key ID
     */
    private String accessKeyId;

    /**
     * key密钥
     */
    private String accessKeySecret;

    /**
     * 文件服务器根目录
     */
    private String bucketName;

    /**
     * 延迟生效时间 单位:h
     */
    private int delayHour;

    /**
     * 远端路径
     */
    private String remotePath;

}
