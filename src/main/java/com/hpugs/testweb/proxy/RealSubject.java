package com.hpugs.testweb.proxy;

import com.hpugs.testweb.utils.PrintUtil;

public class RealSubject implements Subject {

    @Override
    public void operation() {
        PrintUtil.print(getClass(), "进入了operation方法");
    }

}
