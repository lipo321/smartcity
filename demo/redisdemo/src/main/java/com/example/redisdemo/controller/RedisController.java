package com.example.redisdemo.controller;

import com.example.redisdemo.entity.UserEntity;
import com.example.redisdemo.utils.RedisUtil;
import com.fasterxml.jackson.databind.JsonSerializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author lipoGiser
 * @date 2019/11/15 14:30
 * @Version 0.1
 */
@RequestMapping("/redis")
@RestController
public class RedisController {
    private static int ExpireTime = 60;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/hello")
    public String hello(){
        return  "hello";
    }
    @GetMapping("/set")
    public boolean redisSet(String key,String value){
        UserEntity userEntity = new UserEntity();
      //  userEntity.setId(Long.valueOf(1));
   //     userEntity.setGuid(String.valueOf(1));
//        userEntity.setName(new  String("zhangsan"));
  //      userEntity.setAge(String.valueOf(20));
       // userEntity.setCreateTime(new Date());

        return redisUtil.set(key, value,ExpireTime);

    }

    @GetMapping("/get")
    public Object redisGet(String key){
        return redisUtil.get(key);
    }


}
