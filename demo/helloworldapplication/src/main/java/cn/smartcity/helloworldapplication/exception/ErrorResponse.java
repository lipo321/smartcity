package cn.smartcity.helloworldapplication.exception;

import lombok.Data;

/**
 * @author lipoGiser
 * @date 2019/12/31 10:26
 * @Version 0.1
 * @des 新建异常信息实体类
 */
@Data
public class ErrorResponse {
    private String message;
    private String errorTypeName;

    public ErrorResponse(Exception e) {
        this(e.getClass().getName(), e.getMessage());
    }

    public ErrorResponse(String errorTypeName, String message) {
        this.errorTypeName = errorTypeName;
        this.message = message;
    }


}
