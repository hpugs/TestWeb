package com.hpugs.testweb.filter;

import com.hpugs.testweb.utils.PrintUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器测试
 * 过滤类型：2、有路径无顺序
 * 注意：@Component不需要添加了，否则会导致自定义过滤器同时注册为[无路径无顺序]过滤器
 */
@WebFilter(filterName = "UserLoginFilter", urlPatterns = {"/login"})
public class UserLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        PrintUtil.print(getClass(), "进入了init方法");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        PrintUtil.print(getClass(), "进入了destroy方法");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        PrintUtil.print(getClass(), "进入了doFilter方法");
        chain.doFilter(request, response);
    }

}
