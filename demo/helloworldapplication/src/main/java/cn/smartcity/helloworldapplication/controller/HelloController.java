package cn.smartcity.helloworldapplication.controller;

import cn.smartcity.helloworldapplication.domain.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author lipoGiser
 * @date 2019/12/30 15:54
 * @Version 0.1
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String greeting(@RequestParam(name = "name",required = false,defaultValue = "World")
                                       String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }



    @PostMapping("/helloPerson")
    @ResponseBody
    public Person greeting(@RequestBody Person person){
        return person;
    }


}
