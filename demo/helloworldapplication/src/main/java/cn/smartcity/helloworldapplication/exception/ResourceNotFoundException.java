package cn.smartcity.helloworldapplication.exception;

/**
 * @author lipoGiser
 * @date 2019/12/31 10:34
 * @Version 0.1
 * @des 自定义异常类型
 */
public class ResourceNotFoundException extends RuntimeException  {
    private String message;

    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
