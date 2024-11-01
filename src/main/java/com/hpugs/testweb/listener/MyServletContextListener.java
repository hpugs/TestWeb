package com.hpugs.testweb.listener;

import com.hpugs.testweb.utils.PrintUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 监听器，它也是Servlet层面的内容，可用于监听Web应用中某些对象或者信息的创建、修改和销毁等动作发生，并作出相应的响应处理。
 *
 * ServletContext对象监听器。对应于application，需要实现ServletContextListener接口。
 * 在整个Web服务中只有一个，在Web服务关闭时销毁。
 * 可用于做数据缓存，如结合redis可在Web服务创建时从数据库加载数据到缓存服务器中，提升系统响应速度。
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        PrintUtil.print(getClass(), "进入contextInitialized方法");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        PrintUtil.print(getClass(), "进入contextDestroyed方法");
    }
}
