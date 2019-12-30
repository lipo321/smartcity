package cn.smartcity.springboot.controller;

import cn.smartcity.springboot.dao.UserTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lipoGiser
 * @date 2019/12/13 12:03
 * @Version 0.1
 */
@Controller
public class UserController {
    @Autowired
    private UserTest userTest;

    @GetMapping("/test")
    @ResponseBody
    public String test(){
       // userTest.setName("236984");
        return "我叫"+ userTest.getName()+"今年"+ userTest.getAge()+"岁";
    }

}
