package pers.avc.wechat.admin.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pers.avc.wechat.admin.config.AppInfo;
import pers.avc.wechat.admin.contants.WeChatAdminContants;
import pers.avc.wechat.common.constants.WeChatURLConstants;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@Slf4j
@Component
public class AccessTokenService {

    private final static String ACCESS_TOKEN_KEY = "access_token:%s";

    private final static int TOKEN_EXPIRE_IN = 7200;

    @Autowired
    private AppInfo appInfo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ValueOperations<String,String> valueOperations;

    public String getAccessToken() {
        String appId = appInfo.getAppId();
        String appSecret = appInfo.getAppSecret();
        String key = buildAccessTokenKey(appId);
        if (stringRedisTemplate.hasKey(key)) {
            log.info("从Redis获取token......");
            return valueOperations.get(key);
        }
        String url = String.format(WeChatURLConstants.GET_ACCESS_TOKEN, appId, appSecret);
        String respResult = restTemplate.getForObject(url, String.class);
        JSONObject retObject = JSONObject.parseObject(respResult);
        String token = retObject.getString(WeChatAdminContants.ACCESS_TOKEN_KEY);
        log.info("获取token请求返回结果:{}", respResult);
        if (token != null) {
            valueOperations.set(key, token, TOKEN_EXPIRE_IN, TimeUnit.SECONDS);
        } else {
            log.warn("获取token失败: {}", respResult);
        }
        return token;
    }


    private String buildAccessTokenKey(String appId) {
        return String.format(ACCESS_TOKEN_KEY, appId);
    }
}
