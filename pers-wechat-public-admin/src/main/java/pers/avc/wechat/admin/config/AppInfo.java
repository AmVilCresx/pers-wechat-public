package pers.avc.wechat.admin.config;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 账号配置
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@Configuration
@ConfigurationProperties(prefix = "wechat")
public class AppInfo {

    private String appId;

    private String appSecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}