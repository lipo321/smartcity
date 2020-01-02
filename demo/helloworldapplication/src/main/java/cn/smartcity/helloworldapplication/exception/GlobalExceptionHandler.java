package cn.smartcity.helloworldapplication.exception;

import cn.smartcity.helloworldapplication.controller.ExceptionController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lipoGiser
 * @date 2019/12/31 10:40
 * @Version 0.1
 * @des 新建异常处理类，在该类上加上@ControllerAdvice 注解这个类就成为了全局异常处理类，当然
 * 你也可以通过assignableTypes指定特定的Controller类，让异常处理类只处理特地类抛出的异常
 */
@ControllerAdvice(assignableTypes = {ExceptionController.class})
@ResponseBody
public class GlobalExceptionHandler {
    ErrorResponse illegalArgumentResponse = new ErrorResponse(new IllegalArgumentException("参数错误！"));
    ErrorResponse resourseNotFoundResponse = new ErrorResponse(new ResourceNotFoundException("Sorry, the resourse not found!"));

    //拦截所有异常，这里只是为了演示，一般情况下一个方法特定处理一种异常
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e){
        if (e instanceof IllegalArgumentException){
            return ResponseEntity.status(400).body(illegalArgumentResponse);
        }else if(e instanceof ResourceNotFoundException){
            return ResponseEntity.status(404).body(resourseNotFoundResponse);
        }
        return  null;
    }
}
