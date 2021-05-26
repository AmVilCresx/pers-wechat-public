package pers.avc.wechat.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@SpringBootApplication
@MapperScan(basePackages = {"pers.avc.wechat.admin.mapper"})
public class WeChatAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeChatAdminApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
