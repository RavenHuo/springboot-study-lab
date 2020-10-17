package com.raven.api.encryption.filter;

import com.raven.api.encryption.util.AESUtils;
import com.raven.api.encryption.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;
import java.util.Map;

/**
 * @description: 请求处理包装类
 * @author: huorw
 * @create: 2020-10-15 15:29
 */
@Slf4j
public class RequestParamWrapper extends HttpServletRequestWrapper {

    private static final String POST_REQUEST_METHOD = "POST";
    private static final String PUT_REQUEST_METHOD = "PUT";

    private byte[] body;

    public RequestParamWrapper(HttpServletRequest request, String pubKey) {
        super(request);
        try {
            if (checkIfMatchMethod()) {
                String json = RequestUtils.getRequestBody(request);
                //POST请求体参数解密
                if (StringUtils.isNotBlank(json)) {
                    body = AESUtils.decryption(json, pubKey).getBytes();
                }

            } else if (StringUtils.isNotBlank(request.getQueryString())) {
                decryptParameterMap(request, pubKey);
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

    private void decryptParameterMap(HttpServletRequest request, String pubKey) throws Exception{
        Map<String, String[]> param = request.getParameterMap();
        param.forEach((k,v) -> {
            if (v != null) {
                try {
                    log.info("k={},v={}", k,v);
                } catch (Exception e) {
                    log.info("AES 解密出错");
                }
            }
        });

    }

}
