package io.github.forezp.scrorpio.enums;

/**
 * Email miles02@163.com
 *
 * @author fangzhipeng
 * create 2018-07-06
 **/
public enum ErrorCode {
    ERROR_ARGS(1003, "参数不符合要求");


    private int code;
    private String msg;


    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ErrorCode codeOf(int code) {
        for (ErrorCode state : values()) {
            if (state.getCode() == code) {
                return state;
            }
        }
        return null;
    }

}
