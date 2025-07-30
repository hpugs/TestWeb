package com.hpugs.testweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：xinge
 * @Date：2024/11/19 17:20
 * @Description：
 */
@RestController
public class WebStatus {

    @GetMapping("/webStatus")
    public String webStatus() {
        return "success";
    }

}
