package com.stockpredict.system.domain;

import com.stockpredict.system.enums.ResResultCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzhewen
 * @time 2022/3/26 13:03
 * @description: TODO
 */
@Data
public class ResResult<T> implements Serializable {

    /** 是否成功 **/
    private boolean success;
    /** 消息状态码 */
    private int code;
    /** 消息内容 */
    private String msg;
    /** 具体数据 */
    private T value;

    public ResResult() {
    }

    public ResResult(T value) {
        this.success = true;
        this.code = ResResultCodeEnum.SUCCESS.getCode();
        this.msg = ResResultCodeEnum.SUCCESS.getMsg();
        this.value = value;
    }

    public ResResult(String message , T value) {
        this.success = true;
        this.code = ResResultCodeEnum.SUCCESS.getCode();
        this.msg = message;
        this.value = value;
    }

    public ResResult(boolean success, int code, String msg, T value) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.value = value;
    }

    public ResResult(ResResultCodeEnum resultCode, T value) {
        this.success = ResResultCodeEnum.SUCCESS.getCode() == resultCode.getCode();
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.value = value;
    }

    public static <T> ResResult<T> success() {
        return new ResResult<>(null);
    }

    public static <T> ResResult<T> success(String message,T value) {
        return new ResResult<>(null);
    }

    public static <T> ResResult<T> success(T value) {
        return new ResResult<>(value);
    }

    public static <T> ResResult<T> error(String message) {
        return error(ResResultCodeEnum.FAIL, message);
    }

    public static <T> ResResult<T> error(ResResultCodeEnum resultCode) {
        return new ResResult<>(resultCode, null);
    }

    public static <T> ResResult<T> error(ResResultCodeEnum resultCode, String customMsg) {
        return new ResResult<>(false, resultCode.getCode(), customMsg, null);
    }

    public static <T> ResResult<T> error(int code, String customMsg) {
        return new ResResult<>(false, code, customMsg, null);
    }

    public void setResultCode(ResResult<?> other) {
        if (other != null) {
            this.setCode(other.getCode());
            this.setMsg(other.getMsg());
            this.setSuccess(other.isSuccess());
        }

    }

    public void setResultCode(ResResultCodeEnum resultCode) {
        if (resultCode != null) {
            this.setCode(resultCode.getCode());
            this.setMsg(resultCode.getMsg());
            this.setSuccess(ResResultCodeEnum.SUCCESS == resultCode);
        }
    }

    @Override
    public String toString() {
        return String.format("ResResult {success=%s,code=%s,msg=%s,value=%s}", success, code, msg, value);
    }

}
