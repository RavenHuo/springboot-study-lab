package com.raven.api.encryption.zuul.rsa;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.raven.api.encryption.common.ApiEncryptionConstant;
import com.raven.api.encryption.wrapper.AesRequestParamWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: huorw
 * @create: 2020-10-19 11:42
 */
@Slf4j
public class ApiEncryptionZuulPreFilter extends ZuulFilter {

    @Value(value = "${"+ ApiEncryptionConstant.SPRING_API_ENCRYPTION_RSA_PRI_KEY+"}")
    private String rsaPriKey;

    @Value(value = "${"+ApiEncryptionConstant.SPRING_API_ENCRYPTION_RSA_PUB_KEY+"}")
    private String rsaPubKey;

    /**
     * 公钥
     */
    @Value("${"+ApiEncryptionConstant.SPRING_API_ENCRYPTION_AES_PUB_KEY+"}")
    private String aesPubKey;

    /**
     * 返回一个字符串代表过滤器的类型
     * pre表示被路由之前
     *
     * 路由映射主要通过pre类型的过滤器完成，它将请求路径与配置的路由规则进行匹配，以找到需要转发的目标地址；
     * 而请求转发的部分则是由route类型的过滤器来完成，对pre类型过滤器获得的路由地址进行转发。所以说，过滤器可以说是zuul实现api网关功能最核心的部件
     *
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * a "true" return from this method means that the run() method should be invoked
     *
     * @return true if the run() method should be invoked. false will not invoke the run() method
     */
    public boolean shouldFilter() {
        return true;
    }


    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        try {
            // 打印请求内容
            log.info("===============请求内容start===============");
            log.info("请求地址 ={}", request.getRequestURL());
            log.info("请求方式 ={}", request.getMethod());

            request = new AesRequestParamWrapper(request, aesPubKey);

            return null;


        } catch (Exception e) {
            log.error("zuul网关解密出错 e={}", e);
            return null;
        }

    }
}
