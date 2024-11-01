package com.hpugs.testweb.listener;

import com.hpugs.testweb.utils.PrintUtil;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * HttpSession对象监听器。对应session会话，需要实现HttpSessionListener接口。
 * 在会话起始时创建，当一端关闭会话后销毁。可用于获取在线的用户数量。
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
        PrintUtil.print(getClass(), "进入sessionCreated方法");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
        PrintUtil.print(getClass(), "进入sessionDestroyed方法");
    }
}
