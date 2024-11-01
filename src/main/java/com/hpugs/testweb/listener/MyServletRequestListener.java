package com.hpugs.testweb.listener;

import com.hpugs.testweb.utils.PrintUtil;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * SpringBoot实现监听器有两种方式:
 *  一种是直接在实现了对应接口的类上添加@Component注解；
 *  另一种则是先在实现类上添加@WebListener注解，再在项目启动类上添加@ServletComponentScan注解。
 *
 * ServletRequest对象监听器。对应request，需要实现ServletRequestListener接口。
 * request对象是客户发送请求时创建的，用于封装请求数据，当请求处理完毕时，才销毁。可用于封装用户信息。
 */
@WebListener
public class MyServletRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequestListener.super.requestDestroyed(sre);
        PrintUtil.print(getClass(), "进入requestDestroyed方法");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequestListener.super.requestInitialized(sre);
        PrintUtil.print(getClass(), "进入requestInitialized方法");
    }
}
