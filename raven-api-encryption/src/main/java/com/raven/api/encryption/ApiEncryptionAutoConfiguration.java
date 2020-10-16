package com.raven.api.encryption;

import com.raven.api.encryption.common.ApiEncryptionConstant;
import com.raven.api.encryption.feign.FeignInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-12 18:59
 */
@ConditionalOnProperty(value = ApiEncryptionConstant.SPRING_API_ENCRYPTION_SWITCH, matchIfMissing = true)
public class ApiEncryptionAutoConfiguration {

    @Bean
    private FeignInterceptor feignInterceptor() {
        return new FeignInterceptor();
    }
}
