package com.hpugs.testweb.filter.more;

import com.hpugs.testweb.utils.PrintUtil;

import javax.servlet.*;
import java.io.IOException;

public abstract class AbstractFilter implements Filter {

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
