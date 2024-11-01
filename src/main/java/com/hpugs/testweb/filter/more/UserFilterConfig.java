package com.hpugs.testweb.filter.more;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器测试
 * 过滤类型：3、有路径有顺序
 */
@Configuration
public class UserFilterConfig {

    @Bean
    public FilterRegistrationBean getFilter1() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        UserFilter1 userFilter1 = new UserFilter1();
        filterRegistrationBean.setFilter(userFilter1);
        filterRegistrationBean.setName("userFilter1");
        filterRegistrationBean.addUrlPatterns("*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean getFilter2() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        UserFilter2 userFilter2 = new UserFilter2();
        filterRegistrationBean.setFilter(userFilter2);
        filterRegistrationBean.setName("userFilter2");
        filterRegistrationBean.addUrlPatterns("*");
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }

}
