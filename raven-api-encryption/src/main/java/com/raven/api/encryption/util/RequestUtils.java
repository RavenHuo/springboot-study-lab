package com.raven.api.encryption.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-15 16:41
 */
@Slf4j
public class RequestUtils {

    /**
     * 获取请求体内容
     * @param request
     * @return
     */
    public static String getRequestBody(HttpServletRequest request){
        try{
            InputStream in = request.getInputStream();
            return StreamUtils.copyToString(in, StandardCharsets.UTF_8);
        }catch (Exception e) {
            log.info("获取请求体内容 出错 e{}",e);
            return null;
        }
    }
}
