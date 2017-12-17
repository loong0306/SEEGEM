package me.dragon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by dragon on 11/4/2017.
 */
@Configuration
@EnableSwagger2
@Profile({ "dev", "pro" })
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo()).
                select().
                apis(RequestHandlerSelectors.basePackage("me.dragon")).
                paths(PathSelectors.any()).
                build();
    }

    private ApiInfo apiInfo() {
        String email = "i@dragon-yuan.me";
        String name = "dragon";
        String url = "https://www.dragon-yuan.me";
        Contact contact = new Contact(name, url, email);
        return new ApiInfoBuilder().title("项目接口").description("API").contact(contact).build();
    }
}