package pers.avc.wechat.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@Configuration
public class RedisConfig {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisConfig(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 对redis字符串类型数据操作
     *
     * @return
     */
    @Bean
    public ValueOperations<String, String> valueOperations() {
        return stringRedisTemplate.opsForValue();
    }
}
