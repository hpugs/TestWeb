package com.hpugs.testweb.filter;

import com.hpugs.testweb.utils.PrintUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器测试
 * 过滤类型：1、无路径无顺序
 *
 * 过滤器：这里指的是Servlet过滤器，它是在Java Servlet中定义的，能够对Servlet容器中的请求和响应对象进行检查和修改，只起到过滤作用，不会生成Request和Response对象。
 * 过滤器特点：（1）过滤器是基于回调函数实现；（2）过滤器是Servlet规范规定的，只能用于Web程序中；（3）过滤器只在Servlet启动前后起作用，作用范围较窄。
 */
@Component
public class UserFilter implements Filter {

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
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        chain.doFilter(request, response);
    }
}
