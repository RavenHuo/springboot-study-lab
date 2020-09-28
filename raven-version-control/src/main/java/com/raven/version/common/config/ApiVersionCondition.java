package com.raven.version.common.config;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 接口RequestCondition是Spring MVC对一个请求匹配条件的概念建模。最终的实现类可能是针对以下情况之一：路径匹配，头部匹配，请求参数匹配
 *
 *
 * @author raven*/
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    /**
     * 匹配 V[数字]多位/
     */
    private static final Pattern VERSION_PREFIX_PATTERN = Pattern.compile("V(\\d+)/");

    private int apiVersion;

    public ApiVersionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * <p>Title: combine</p>
     * <p>
     * Description: 合并
     * </p>
     *
     * @param apiVersionCondition
     * @return
     */
    @Override
    public ApiVersionCondition combine(ApiVersionCondition apiVersionCondition) {
        return new ApiVersionCondition(apiVersionCondition.getApiVersion());
    }

    /**
     * 跟据url及正则，匹配出该url存在的版本号
     * 返回 url中的版本号<=version的url
     * @param httpServletRequest
     * @return
     */
    @Nullable
    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest httpServletRequest) {

        Matcher matcher = VERSION_PREFIX_PATTERN.matcher(httpServletRequest.getRequestURI());

        if (matcher.find()) {
            Integer version = Integer.valueOf(matcher.group(1));
            if (version >= this.apiVersion) {
                return this;
            }
        }

        return null;
    }

    /**
     * compareTo 方法顾名思义是比较方法
     * 该方法比较的是版本号，然后选择版本号（找出最大的版本号）
     * @param apiVersionCondition
     * @param httpServletRequest
     * @return
     */
    @Override
    public int compareTo(ApiVersionCondition apiVersionCondition, HttpServletRequest httpServletRequest) {
        return apiVersionCondition.getApiVersion() - this.apiVersion;
    }

    private int getApiVersion() {
        return apiVersion;
    }
}
