package com.hsm.quartztask.handler;

import com.hsm.quartztask.common.ResponseBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/9 13:44
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseBO defaultErrorHandler(Exception ex, HttpServletRequest request) {
        log.error(">>服务器内部异常: {}", request.getRequestURI(), ex);
        return ResponseBO.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseBO methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("参数异常 info:{}", ex.getMessage());
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        StringBuffer errorMsg = new StringBuffer();
        errors.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
        return ResponseBO.failure(errorMsg.toString());
    }
}
