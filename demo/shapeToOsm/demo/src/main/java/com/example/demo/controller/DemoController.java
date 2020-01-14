package com.example.demo.controller;

import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
@RestController
@RequestMapping("demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping("/test")
    public String demoTest(@RequestParam("name") String name) {
        return demoService.aopDemo(name, 1);
    }
}
