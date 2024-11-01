package com.hpugs.testweb.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class MyAopController {

    @GetMapping("/test")
    public String test(){
        String result = "aop test success";
        System.out.println(result);
        return result;
    }

    @GetMapping("/testThrowException")
    public String testThrowException(){
        String result = "aop testThrowException success";
        System.out.println(result);
        throw new RuntimeException("testThrowException");
    }
}
