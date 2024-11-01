package com.hpugs.testweb.proxy.staticproxy;

import com.hpugs.testweb.proxy.Subject;
import com.hpugs.testweb.utils.PrintUtil;

/**
 * 定义一个RealSubject的静态代理类
 */
public class RealSubjectProxy implements Subject {

    private Subject realSubject;

    public RealSubjectProxy(Subject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void operation() {
        // 对代理方法进行增强
        before();
        realSubject.operation();
        after();
    }

    private void before(){
        PrintUtil.print(getClass(), "进入了before方法");
    }

    private void after(){
        PrintUtil.print(getClass(), "进入了after方法");
    }

}
