package com.stockpredict.system.enums;


/**
 * 返回值的code定义
 *
 * @author wuque
 * @since 2021.09.29
 */
public enum ResResultCodeEnum {

    /**
     * 内部状态码，调用方请不要直接使用
     */
    SUCCESS(1001, "Request succeeded."),
    FAIL(2001, "Processing failed."),
    PARAM_ERROR(2002, "Parameter error."),
    OPERATION_TOO_FREQUENT(2003, "The operation is too frequent, please try again later."),
    OPERATION_EXPIRED(2004, "The operation is expired."),
    LOGIN_STATUS_CHECK_FAIL(2005, "Please login first."),
    OPERATION_FORBIDDEN(2006, "The operation is forbidden."),
    TARGET_NOT_EXISTS(2007, "Target does not exist."),
    REQUEST_LIMITED(2008, "Request limited, please try again later. "),
    TARGET_ALREADY_EXISTS(2009, "Target already exists."),

    NOT_A_DAG(2010, "There are loops in dependencies."),
    ;

    private final int code;
    private final String msg;

    ResResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
