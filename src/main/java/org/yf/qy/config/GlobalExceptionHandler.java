package org.yf.qy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.yf.qy.vo.Response;

/**
 * @author yfqlzlx
 * @date 2019/8/6 15:46
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e){
        log.error("错误：【{}】,报错信息：【{}】",e.getLocalizedMessage(),e);
        return new Response(500,e.getMessage());
    }
}
