package com.hpugs.testweb.proxy.cjlibproxy;


import com.hpugs.testweb.utils.PrintUtil;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cjlib实现动态代理
 */
public class CjlibProxy2 implements MethodInterceptor {

    public Object createProxy(Class<?> tClass){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(tClass);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // 对代理方法进行增强
        before();
        // invokeSuper调用的是被代理类的方法, 但只有代理类才存在基类, 必须使用代理类作为obj参数调用
        // invoke调用的是增强方法, 必须使用被代理类的对象调用, 使用代理类会造成OOM
        Object result = methodProxy.invokeSuper(obj, args);
        after();
        return result;
    }

    private void before(){
        PrintUtil.print(getClass(), "进入了before方法");
    }

    private void after(){
        PrintUtil.print(getClass(), "进入了after方法");
    }

}
