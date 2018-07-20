package com.djk.filter;

import com.djk.TokenContextHandler;
import com.djk.authority.Authority;
import com.djk.authority.AuthorityRibbon;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by dujinkai on 2018/7/11.
 * 权限过滤器
 */
@Slf4j
@Component
public class AuthorityFilter extends ZuulFilter {


    /**
     * 忽略的url
     */
    @Value("${ignore.authUrls}")
    private String ignoreUrls;

    /**
     * 注入权限接口服务
     */
    @Autowired
    private AuthorityRibbon authorityRibbon;

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        // 判断是否需要过滤  不需要过滤 直接放通
        if (!isNeedFilter(request.getRequestURI())) {
            ctx.setSendZuulResponse(true);
            return null;
        }

        // 没有权限 则直接返回
        if (!hasAuthority(request.getRequestURI(), authorityRibbon.queryManagerAuthority(), request.getMethod())) {

            log.error("you has no authority and uri:" + request.getRequestURI());

            ctx.setResponseStatusCode(468);
            ctx.setSendZuulResponse(false);
            try {
                ctx.getResponse().getWriter().write("you has no authority...");
                ctx.getResponse().getWriter().close();
            } catch (Exception e) {
                log.error("fail...", e);
            }

            TokenContextHandler.remove();
            return null;
        }

        TokenContextHandler.remove();

        return null;
    }

    /**
     * 判断是否需要过滤
     *
     * @param uri 请求的地址
     * @return 需要过滤返回true  不需要过滤返回false
     */
    private boolean isNeedFilter(String uri) {

        log.debug("isNeedFilter and uri:{} \r\n ignoreUrls:{} ", uri, ignoreUrls);

        // 没有忽略的url 直接返回
        if (StringUtils.isEmpty(ignoreUrls)) {
            log.info("ignoreUrls is empty....");
            return true;
        }

        return !Stream.of(ignoreUrls.split(",")).filter(ignoreUrl -> uri.contains(ignoreUrl)).findAny().isPresent();
    }


    /**
     * 判断是否有权限
     *
     * @param url         用户访问的url
     * @param authorities 权限信息
     * @param action      动作
     * @return 有权限返回true  没有权限返回false
     */
    private boolean hasAuthority(String url, Authority[] authorities, String action) {

        if (ArrayUtils.isEmpty(authorities)) {
            return false;
        }

        List a = Stream.of(authorities).filter(authority1 -> !StringUtils.isEmpty(authority1.getUrl())).collect(Collectors.toList());

        // 匹配出所有满足的url
        List<Authority> authorityList = Stream.of(authorities).filter(authority1 -> !StringUtils.isEmpty(authority1.getUrl())).filter(authority -> url.indexOf(authority.getUrl()) != -1).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(authorityList)) {
            return false;
        }
        // 在满足的url上面匹配action （restful请求可能同一个url 但是method不同）
        return !CollectionUtils.isEmpty(authorityList.stream().filter(authority -> action.equalsIgnoreCase(authority.getAction())).collect(Collectors.toList()));
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }
}
