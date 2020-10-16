package com.raven.api.encryption.filter;

import com.raven.api.encryption.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @description: 请求处理包装类
 * @author: huorw
 * @create: 2020-10-15 15:29
 */
@Slf4j
public class RequestParamWrapper extends HttpServletRequestWrapper {

    private static final String POST_REQUEST_METHOD = "POST";
    private static final String PUT_REQUEST_METHOD = "PUT";

    private String pubKey;

    private byte[] body;

    public RequestParamWrapper(HttpServletRequest request, String pubKey) {
        super(request);
        if (checkIfMatchMethod()) {
            String json = RequestUtils.getRequestBody(request);
            //POST请求体参数解密
            if (StringUtils.isNotBlank(json)) {
//                body =
            }

        } else if (StringUtils.isNotBlank(request.getQueryString())) {

        } else {
            log.info("不进行加解密");
        }
    }

    /**
     * 判断是否为 post或put请求
     * @return
     */
    private Boolean checkIfMatchMethod() {
        return POST_REQUEST_METHOD.equals(this.getMethod()) || PUT_REQUEST_METHOD.equals(this.getMethod());
    }



}
