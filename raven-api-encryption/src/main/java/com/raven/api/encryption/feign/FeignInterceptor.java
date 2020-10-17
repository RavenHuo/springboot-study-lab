package com.raven.api.encryption.feign;

import com.raven.api.encryption.common.ApiEncryptionConstant;
import com.raven.api.encryption.controller.Routes;
import com.raven.api.encryption.util.AESUtils;
import com.raven.api.encryption.util.RequestUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-12 18:54
 */
@Slf4j
public class FeignInterceptor implements RequestInterceptor {

    /**
     * 公钥
     */
    @Value("${"+ ApiEncryptionConstant.SPRING_API_ENCRYPTION_AES_PUB_KEY+"}")
    private String pubKey;

    /**
     * 在这里处理feign参数
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        if (!Routes.ENCRYPTION_EXCLUDE_URL.contains(template.url())) {
            log.debug("excludeUrl 不包含 该url");
            byte[] body = template.body();
            Map<String, Collection<String>> queries = template.queries();
            try {
                if (null != body) {
                    String encryptionStr = AESUtils.encryption(String.valueOf(body), pubKey);

                    Map<String, Collection<String>> newQueries = new HashMap<>();
                    queries.forEach((k, v) -> {
                        String value = String.valueOf(v);
                        try {
                            newQueries.put(k, Arrays.asList(StringUtils.split(AESUtils.encryption(value, pubKey))));
                        } catch (Exception e) {
                            log.info("加密 queries失败 k={}， v={}", k, v);
                        }
                    });
                    template.body(encryptionStr);
                    template.queries(newQueries);
                    log.info("加密成功");

                } else{
                    log.info("加密失败 ---- 暂不加密");
                }
            }catch (Exception e) {
                log.info("加密失败 ---- 暂不加密");
                template.body(String.valueOf(body));
            }
        }
    }
}
