package cn.smartcity.helloworldapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author lipoGiser
 * @date 2019/12/31 11:14
 * @Version 0.1
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourseNotFoundException2 extends RuntimeException {

    public ResourseNotFoundException2(){
    }

    public ResourseNotFoundException2(String message){
        super(message);
    }


}
