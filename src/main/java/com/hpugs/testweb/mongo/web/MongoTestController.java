package com.hpugs.testweb.mongo.web;

import com.hpugs.testweb.dto.UserDTO;
import com.hpugs.testweb.mongo.MongoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mongo")
public class MongoTestController {

    @Resource
    private MongoService mongoService;

    @PostMapping("/insert")
    public String insert(@RequestBody UserDTO userDTO){
        mongoService.insert(userDTO);
        return "success";
    }

    @GetMapping("/queryAll")
    private String queryAll(){
        return mongoService.queryAll();
    }

    @GetMapping("/queryByAge")
    private String queryByAge(@RequestParam("age") Integer age){
        return mongoService.queryByAge(age);
    }

    @PostMapping("/update")
    private String update(@RequestBody UserDTO userDTO){
        mongoService.update(userDTO);
        return "success";
    }

    @PostMapping("/delete")
    private String delete(@RequestBody UserDTO userDTO){
        mongoService.delete(userDTO);
        return "success";
    }

}
