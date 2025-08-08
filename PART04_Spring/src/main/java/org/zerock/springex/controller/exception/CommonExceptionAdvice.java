package org.zerock.springex.controller.exception;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(NumberFormatException.class)
    public String exceptNumber(NumberFormatException numberFormatException) {

        log.error("-----------------------------------");
        log.error(numberFormatException.getMessage());

        return "NUMBER FORMAT EXCEPTION";

    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptCommon(Exception exception){


        StringBuffer buffer = new StringBuffer("<ul>");

        log.error("-----------------------------------");
        log.error(exception.getMessage());
        buffer.append("<li>" +exception.getMessage()+"</li>");

        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>"+stackTraceElement+"</li>");
        });
        buffer.append("</ul>");

        return buffer.toString();
    }
    @ExceptionHandler(NoHandlerFoundException.class) // 404에러 : 페이지를 찾을 수 없을때 나오는 에러 페이지에 대한 처리
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(){

        return "custom404";
    }


}
