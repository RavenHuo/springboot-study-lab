package com.raven.api.encryption.filter;

import com.raven.api.encryption.util.AESUtils;
import com.raven.api.encryption.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 请求处理包装类
 * @author: huorw
 * @create: 2020-10-15 15:29
 */
@Slf4j
public class RequestParamWrapper extends HttpServletRequestWrapper {

    private static final String POST_REQUEST_METHOD = "POST";
    private static final String PUT_REQUEST_METHOD = "PUT";

    private HashMap<String, String[]> parameters = null;

    private static String publicKey;

    private byte[] body;

    public RequestParamWrapper(HttpServletRequest request, String pubKey) {
        super(request);
        publicKey = pubKey;
        parameters = new HashMap<>();
        String json = RequestUtils.getRequestBody(request);
        if (StringUtils.isNotBlank(json)) {
            body = json.getBytes();
        }
        try {
            if (checkIfMatchMethod()) {
                //POST请求体参数解密
                if (StringUtils.isNotBlank(json)) {
                    body = AESUtils.decryption(json, pubKey).getBytes();
                }
            } else {
                log.info("不进行加解密");
            }
        }catch (Exception e) {
            log.info("处理请求内容出错，不处理请求");
        }
    }

    /**
     * 判断是否为 post或put请求
     * @return
     */
    private Boolean checkIfMatchMethod() {
        return POST_REQUEST_METHOD.equals(this.getMethod()) || PUT_REQUEST_METHOD.equals(this.getMethod());
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] value = super.getParameterValues(name);
        if (value != null) {
            try {
                log.info("k={},v={}", name, value);
                value = StringUtils.split(AESUtils.decryption(StringUtils.join(value), publicKey));
                return value;
            } catch (Exception e) {
                log.info("AES 解密出错");
                return value;
            }
        }else {
            return value;
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * 在使用@RequestBody注解的时候，其实框架是调用了getInputStream()方法，所以我们要重写这个方法
     * @return
     * @throws IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

}
