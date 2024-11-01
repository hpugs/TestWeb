package com.hpugs.testweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @ServletComponentScan：注解用于扫描添加@WebFilter、@WebServlet、@WebListener注解的Bean
 */
@SpringBootApplication
@ServletComponentScan
public class TestWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestWebApplication.class, args);
    }

}
