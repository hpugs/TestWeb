package com.hpugs.testweb.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 拦截器，这里指的是Spring中的拦截器，主要用于拦截用户请求并做相应的处理。
 * 拦截器和过滤器、监听器不同，它不依赖于Servlet容器，依赖于Spring框架，是AOP的一种体现，底层基于Java的动态代理实现。
 *
 * 拦截器特点：
 * （1）拦截器基于Java反射机制（动态代理）实现；
 * （2）拦截器是Spring特有的，能使用Spring中的任何资源；
 * （3）拦截器可以用于Web程序，也可以用于Application和Swing程序汇总；
 * （4）拦截器能够深入到方法前后，异常抛出前后，伸缩性较大，作用范围较广。
 *
 * 拦截器是链式调用，一个应用中可以同时存在多个拦截器（Interceptor），一个请求也可以触发多个拦截器，每个拦截器会根据它被声明的顺序依次被调用。
 *
 *
 *
 */
@Configuration
public class WebMvcInterceptorConfig implements WebMvcConfigurer {

    @Resource
    private HandlerInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/login", "/loginOut");
    }
}
