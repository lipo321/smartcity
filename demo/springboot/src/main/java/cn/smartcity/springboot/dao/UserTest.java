package cn.smartcity.springboot.dao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author lipoGiser
 * @date 2019/12/13 14:08
 * @Version 0.1
 */
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "use")
@Component
public class UserTest {
    private String  name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
