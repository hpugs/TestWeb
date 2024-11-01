package com.hpugs.testweb.proxy.jdkproxy;

import com.hpugs.testweb.utils.PrintUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 */
public class JdkProxy implements InvocationHandler {

    private Object target;

    public JdkProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 对代理方法进行增强
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void before(){
        PrintUtil.print(getClass(), "进入了before方法");
    }

    private void after(){
        PrintUtil.print(getClass(), "进入了after方法");
    }

    /**
     * 生成代理对象
     */
    public <T> T newProxyInstance() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

}
