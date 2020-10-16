package com.raven.api.encryption.filter;

import com.raven.api.encryption.common.ApiEncryptionConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-14 22:17
 */
@WebFilter(urlPatterns = "/*", filterName = "ApiEncryptionFilter")
@Slf4j
public class ApiEncryptionFilter implements Filter {

    private static final String POST_REQUEST_METHOD = "POST";

    private static final String PUT_REQUEST_METHOD = "PUT";

    /**
     * 公钥
     */
    @Value(ApiEncryptionConstant.SPRING_API_ENCRYPTION_PUB_KEY)
    private String pubKey;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RequestParamWrapper requestParamWrapper = new RequestParamWrapper((HttpServletRequest) servletRequest, pubKey);
        filterChain.doFilter(requestParamWrapper, servletResponse);
    }
}
