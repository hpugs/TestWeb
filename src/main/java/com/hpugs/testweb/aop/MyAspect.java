package com.hpugs.testweb.aop;

import com.alibaba.fastjson.JSONObject;
import com.hpugs.testweb.utils.PrintUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * AOP（面向切面编程）是一种编程思想，并不是某种具体实现。
 * 一般在提到AOP实现时，会有过滤器和代理模式这两种，过滤器基于函数回调实现，而代理模式基于java反射机制。
 * 代理模式分为静态代理和动态代理，动态代理就是拦截器的简单实现。
 *
 * @Order(1)，AOP切面的执行顺序。注意@Before数值越小越先执行，@After和@AfterReturning则是数值越大越先执行。
 */
@Component
@Aspect
@Order(1)
public class MyAspect {

    /**
     * 用于定义一个切入点，可以是一个规则表达式，也可以是注解；
     */
    @Pointcut("execution(public * com.hpugs.testweb.aop.MyAopController.*(..))")
    public void setPointcut(){}

    @Before("setPointcut()")
    public void before(){
        PrintUtil.print(getClass(), "进入了before方法");
    }

    @After("setPointcut()")
    public void after(){
        PrintUtil.print(getClass(), "进入了after方法");
    }

    /**
     * 用于在切入点前后切入内容，并控制何时执行切入点的内容。可以理解是@Before和@After注解的组合使用；
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("setPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        PrintUtil.print(getClass(), "进入了myAround...before...");
        // 调用切点业务方法，并获取返回结果
        Object result = joinPoint.proceed();
        // 打印请求的返回结果
        PrintUtil.print(getClass(), "进入了myAround...result=" + JSONObject.toJSONString(result));
        PrintUtil.print(getClass(), "进入了myAround...after...");
        return result;
    }

    /**
     * 用于在切入点返回内容之后处理逻辑；
     */
    @AfterReturning("setPointcut()")
    public void afterReturning(){
        PrintUtil.print(getClass(), "进入了afterReturning方法");
    }

    /**
     * 用于当切入内容部分抛出异常之后的处理逻辑
     */
    @AfterThrowing("setPointcut()")
    public void afterThrowing(){
        PrintUtil.print(getClass(), "进入了afterThrowing方法");
    }

}
