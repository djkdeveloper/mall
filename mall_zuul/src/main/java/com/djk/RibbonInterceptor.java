package com.djk;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Created by dujinkai on 2018/7/11.
 * ribbon设置token的拦截器
 */
public class RibbonInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpHeaders headers = httpRequest.getHeaders();
        headers.add("userId", TokenContextHandler.getToken());
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
