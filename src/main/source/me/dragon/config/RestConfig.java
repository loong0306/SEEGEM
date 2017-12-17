package me.dragon.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dragon on 11/4/2017.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = RestConfig.REST_PREFIX)
public class RestConfig {
    public final static String REST_PREFIX = "restful";

    private String corUrl;
}
