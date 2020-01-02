package cn.smartcity.helloworldapplication.controller;

import cn.smartcity.helloworldapplication.exception.ResourseNotFoundException2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lipoGiser
 * @date 2019/12/31 11:19
 * @Version 0.1
 */
@RestController
@RequestMapping("/api")
public class ResponseStatusExceptionController {

    @GetMapping("/resourceNotFoundException2")
    public void throwException3(){
        throw new ResourseNotFoundException2("Sorry,the resource not found!");
    }
}
