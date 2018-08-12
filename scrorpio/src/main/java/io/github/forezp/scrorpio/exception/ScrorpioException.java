package io.github.forezp.scrorpio.exception;

import io.github.forezp.scrorpio.enums.ErrorCode;

/**
 * Email miles02@163.com
 *
 * @author fangzhipeng
 * create 2018-07-06
 **/
public class ScrorpioException extends RuntimeException {

    private ErrorCode errorCode;

    public ScrorpioException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public ScrorpioException(ErrorCode errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }


    public int getCode() {
        return errorCode.getCode();
    }

    public String getMsg() {
        return errorCode.getMsg();
    }
}
