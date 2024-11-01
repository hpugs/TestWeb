package com.hpugs.testweb.proxy;

import com.hpugs.testweb.proxy.cjlibproxy.CjlibProxy1;
import com.hpugs.testweb.proxy.cjlibproxy.CjlibProxy2;
import com.hpugs.testweb.proxy.jdkproxy.JdkProxy;
import com.hpugs.testweb.proxy.staticproxy.RealSubjectProxy;

public class ProxyTest {

    public static void main(String[] args) {
        // 静态代理测试
//        staticProxyTest();

        // jdk动态代理测试
//        jdkProxyTest();

        // cjlib动态代理测试
//        cjlibProxyTest1();
        cjlibProxyTest2();
    }

    private static void staticProxyTest() {
        RealSubject realSubject = new RealSubject();
        RealSubjectProxy proxy = new RealSubjectProxy(realSubject);
        proxy.operation();
    }

    private static void jdkProxyTest() {
        Subject subject = new RealSubject();
        JdkProxy jdkProxy = new JdkProxy(subject);
        Subject proxySubject = jdkProxy.newProxyInstance();
        proxySubject.operation();
    }

    private static void cjlibProxyTest1() {
        CjlibProxy1 cjlibProxy = new CjlibProxy1();
        RealSubject realSubjectProxy = (RealSubject) cjlibProxy.createProxy(RealSubject.class);
        realSubjectProxy.operation();
    }

    private static void cjlibProxyTest2() {
        CjlibProxy2 cjlibProxy = new CjlibProxy2();
        RealSubject realSubjectProxy = (RealSubject) cjlibProxy.createProxy(RealSubject.class);
        realSubjectProxy.operation();
    }

}
