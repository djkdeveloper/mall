package com.djk.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by dujinkai on 2018/7/28.
 * feign拦截器
 */
@Component
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("userId", ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("userId"));
    }
}
