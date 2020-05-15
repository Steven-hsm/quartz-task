package com.hsm.quartztask.common;

import lombok.Data;

/**
 * @author huangsenming
 * @Description: 响应返回实体
 * @date 2020/4/9 12:13
 */
@Data
public class ResponseBO<T> {
    private boolean state;//处理成功或者失败

    private int code;

    private String message;

    private T info;

    public ResponseBO() {
    }

    public ResponseBO(boolean state, int code, String message, T info) {
        this.state = state;
        this.code = code;
        this.message = message;
        this.info = info;
    }


    public static final ResponseBO success(String message){
        return new ResponseBO(true, SystemCodeEnum.SUCCESS.getCode(), message, null);
    }
    public static final ResponseBO success(Integer code, String message){
        return new ResponseBO(true,  code, message, null);
    }
    public static final ResponseBO failure(Integer code, String errorMsg){
        return new ResponseBO(false, code, errorMsg, null);
    }
    public static final <T> ResponseBO<T> success(String message, T obj){
        return new ResponseBO<T>(true, SystemCodeEnum.SUCCESS.getCode(),message, obj);
    }

    public static final ResponseBO success(){
        return new ResponseBO(true,SystemCodeEnum.SUCCESS.getCode(), null, null);
    }

    public static final ResponseBO failure(Integer code){

        return new ResponseBO(false,code, null, null);
    }

    public static final ResponseBO failure(String message){
        return new ResponseBO(false,SystemCodeEnum.ERROR.getCode(), message, null);
    }

    public static final ResponseBO failure(SystemCodeEnum systemCodeEnum){
        return new ResponseBO(false,systemCodeEnum.getCode(), systemCodeEnum.getMessage(), null);
    }


    public static  final <T> ResponseBO<T> success(T obj){
        return new ResponseBO<>(true, SystemCodeEnum.SUCCESS.getCode(),null, obj);
    }
}
