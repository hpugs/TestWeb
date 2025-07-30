package com.hpugs.testweb;

import com.hpugs.testweb.excel.ExcelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 过滤器、监听器、拦截器、AOP学习测试
 *
 * Filter、ServletContextListener、Interceptor和AOP以及经常用到的@ControllerAdvice注解，它们的执行顺序如下所示：
 *
 * ServletContextListener-->Filter-->Interceptor-->AOP-->具体执行的方法-->AOP-->@ControllerAdvice-->Interceptor-->Filter-->ServletContextListener
 *
 * 上述对象根据实现原理可分为两大类：
 *
 * （1）Filter和Listener：依赖Servlet容器，基于函数回调实现，几乎可拦截所有请求，但无法获取Spring IOC容器中的Bean对象。
 *
 * （2）Interceptor和AOP：依赖Spring框架，基于Java反射和动态代理实现，只能拦截Controller中的请求，可以获取Spring IOC容器中的Bean对象。
 *
 * 可以看到从Filter-->Interceptor-->AOP，拦截功能越来越强大，尤其是Interceptor和AOP可以结合Spring框架来进行实现，但是拦截顺序确是越来越往后，所以如果有些请求可以在Servlet容器中过滤，那么这是最好的，毕竟越早的过滤和拦截就消耗越少的系统资源。
 */
@RestController
public class UserController {

    @Resource
    private ExcelService excelService;

    @GetMapping("/login")
    public String login(){
        String result = "login success";
        System.out.println(result);
        return result;
    }

    @GetMapping("/loginOut")
    public String loginOut(){
        String result = "loginOut success";
        System.out.println(result);
        return result;
    }

    @GetMapping("/setSession")
    public String setSession(HttpServletRequest request, HttpServletResponse response){
        String result = "setSession success";
        HttpSession session = request.getSession();
        session.setAttribute("env", "home");
        System.out.println(result);
        return result;
    }

    @GetMapping("/removeSession")
    public String removeSession(HttpServletRequest request, HttpServletResponse response){
        String result = "removeSession success";
        HttpSession session = request.getSession();
        session.removeAttribute("env");
        System.out.println(result);
        return result;
    }

    @GetMapping("/downloadFile")
    public void downloadFile(@RequestParam(name = "fileType") Integer fileType, HttpServletResponse response){
        excelService.downloadFileTemplate(fileType, response);
    }

}
