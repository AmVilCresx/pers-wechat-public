package pers.avc.wechat.core;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.fastjson.JSONObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import pers.avc.wechat.core.dto.BaseMessageDto;
import pers.xml2bean.springboot.core.XmlUtils;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@SpringBootApplication
@MapperScan(basePackages = {"pers.avc.wechat.core.mapper"})
// @NacosPropertySource(dataId = "admin", autoRefreshed = true,groupId = "WECHAT_ADMIN")
public class WeChatCoreApplication {

    public static void main(String[] args) {
        //SpringApplication.run(WeChatCoreApplication.class, args);
        ConfigurableApplicationContext context = new SpringApplicationBuilder(WeChatCoreApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
        BaseMessageDto msg = new BaseMessageDto("toUser", "fromUser", 123456L, "text", 888L);

        // context.close();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
