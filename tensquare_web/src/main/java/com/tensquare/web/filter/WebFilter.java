package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

@Component
public class WebFilter extends ZuulFilter {

    @Override
    public String filterType(){
        //前置过滤器
        return "pre";
    }
    @Override
    public int filterOrder(){
        //优先级为0，数字越大，优先级越低
        return 0;
    }
    @Override
    public Object run() throws ZuulException {
        System.out.println("zuul过滤器。。。");
        return null;
    }

    @Override
    public boolean shouldFilter() {

        //是否执行该过滤器，此处为true,说明需要过滤
        return true;
    }
}
