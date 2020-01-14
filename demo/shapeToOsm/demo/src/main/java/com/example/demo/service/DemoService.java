package com.example.demo.service;

import org.springframework.stereotype.Service;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
@Service
public class DemoService {
    public String aopDemo(String param1, Integer param2) {
        return "aopDemo!" + param1 + param2;
    }
}
