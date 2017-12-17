package me.dragon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by dragon on 11/4/2017.
 */
@Configuration
@ImportResource({"classpath:spring-config-aop.xml"})
public class AopConfig {
}
