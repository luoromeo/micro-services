package com.luoromeo.shiro.config.redis;

import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月17日 16:52
 * @modified By
 */
@Configuration
@ComponentScan
@EnableCaching
public class RedissonConfig {
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson(@Value("classpath:redis/spring-redisson.yaml") Resource configFile) throws IOException {
        Config config = Config.fromYAML(configFile.getInputStream());
        return Redisson.create(config);
    }

    @Bean("redisCacheManager")
    public CacheManager cacheManager(RedissonClient redissonClient) {
        return new RedissonSpringCacheManager(redissonClient, "classpath:redis/spring-cache-config.yaml");
    }
}
