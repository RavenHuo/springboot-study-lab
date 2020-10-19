package com.raven.api.encryption;

import com.raven.api.encryption.common.ApiEncryptionConstant;
import com.raven.api.encryption.zuul.rsa.ApiEncryptionZuulPreFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-12 18:59
 */
@ConditionalOnProperty(value = ApiEncryptionConstant.SPRING_API_ENCRYPTION_SWITCH, matchIfMissing = true)
@Configuration
@ComponentScan({"com.raven.api.encryption"})
@Slf4j
public class ApiEncryptionZuulAutoConfiguration {

    @PostConstruct
    public void init() {
        log.info("ApiEncryptionAutoConfiguration init------------------------");
    }

    @Bean
    public ApiEncryptionZuulPreFilter apiEncryptionZuulPreFilter() {
        return new ApiEncryptionZuulPreFilter();
    }

}
