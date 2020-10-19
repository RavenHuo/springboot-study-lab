package com.raven.api.encryption.filter;

import com.raven.api.encryption.common.ApiEncryptionConstant;
import com.raven.api.encryption.controller.Routes;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-14 22:17
 */
@WebFilter(value = "/encryption/*", filterName = "ApiEncryptionFilter")
@Slf4j
public class ApiEncryptionFilter implements Filter {

    /**
     * 公钥
     */
    @Value("${"+ApiEncryptionConstant.SPRING_API_ENCRYPTION_AES_PUB_KEY+"}")
    private String pubKey;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((RequestFacade) servletRequest).getRequestURI();
        if (Routes.ENCRYPTION_EXCLUDE_URL.contains(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            RequestParamWrapper requestParamWrapper = new RequestParamWrapper((HttpServletRequest) servletRequest, pubKey);
            filterChain.doFilter(requestParamWrapper, servletResponse);
        }
    }
}
