package com.hsm.quartztask.common;

/**
 * @ClassName SystemCodeEnum
 *  系统编码状态，处理整个系统交互状态
 * @author wjs
 * @date 2019/6/24 16:06
 * @version 1.0
 **/
public enum SystemCodeEnum {
    NO_OAUTH(9, "未授权"),

    //正常访问
    SUCCESS(200,"SUCCESS"),
    //访问失败
    ERROR(300,"ERROR"),


    //请求的地址不存在或者包含不支持的参数
    BAD_REQUEST(400,"BAD REQUEST"),
    PARAM_ERROR(401, "参数错误"),
    USER_NOT_EXIST(402, "用户不存在"),
    PASSWORD_ERROR(403, "密码错误"),
    TOKEN_ERROR(404, "token错误"),
    REQUEST_ILLEGAL(405, "请求非法"),

    POWER_INSUFFICIENT(410, "权限不足"),
    SIGN_FAIL(412, "签名失败"),

    NEED_SURETY(413, "需要授权"),
    //内部服务异常
    INTERNAL_SERVER_ERROR(500,"INTERNAL SERVER ERROR");


    private final int code;
    private final String message;

    SystemCodeEnum(int code, String msg){
        this.code = code;
        this.message = msg;
    }


    public int getCode(){
        return code;
    }

    public String getMessage() {
        return message;
    }

}
