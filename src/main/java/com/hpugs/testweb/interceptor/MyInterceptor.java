package com.hpugs.testweb.interceptor;

import com.hpugs.testweb.utils.PrintUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * （1）preHandle()，该方法将在请求处理之前调用。「注意，如果该方法返回值为false，那么就认为当前请求结束，这不仅导致当前的拦截器失效，同时其他的拦截器也不再执行。」
 *
 * （2）postHandle()，该方法只有在preHandle()方法返回true的时候才会执行，且会在Controller中方法调用之后，DispatcherServlet返回渲染视图之前被调用。
 * 「注意，postHandle()方法被调用的顺序与preHandle()是相反的，即先声明的拦截器的preHandle()会先执行，但是它的postHandle()方法反而会在后执行。」
 *
 * （3）afterCompletion()，该方法只有在preHandle()方法返回true的时候才会执行，且会在整个请求结束之后，DispatcherServlet渲染了对应的视图之后执行。
 * 可以在此方法中进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中。
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        PrintUtil.print(getClass(), "进入preHandle方法");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String methodName = method.getName();
        PrintUtil.print(getClass(), " 调用methodName=" + methodName);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        PrintUtil.print(getClass(), "进入postHandle方法");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        PrintUtil.print(getClass(), "进入afterCompletion方法");
    }
}
