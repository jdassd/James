package com.James.corporateportraitplatforms.configuration;

import com.James.corporateportraitplatforms.model.AjaxResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {

    @ExceptionHandler(Exception.class)
    public AjaxResponseModel<Object> error(Exception err) {
        err.printStackTrace();
        log.error("AppExceptionHandler catch error", err);
        return AjaxResponseModel.<Object>builder()
                .code(500)
                .msg(err.getMessage())
                .data(err)
                .build();
    }
}
