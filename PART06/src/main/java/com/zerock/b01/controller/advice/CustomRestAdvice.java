package com.zerock.b01.controller.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class CustomRestAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String,String>> handleBindException(BindException e) {

        log.error(e);

        Map<String,String> errorMap = new HashMap<>();

        if(e.hasErrors()){
            BindingResult bindingResult = e.getBindingResult();

            bindingResult.getFieldErrors().forEach(fieldError -> {
                errorMap.put(fieldError.getField(), fieldError.getCode());
            });
        }
        return ResponseEntity.badRequest().body(errorMap);
    }
}
/* 클라이언트 요청이 들어옴 → DTO 검증(@Valid) 실패 시 BindException 발생.

handleBindException() 메서드가 실행됨.

BindingResult에서 필드별 에러 정보를 꺼냄.

에러 메시지를 Map<String, String> 형태(필드명 → 에러 코드)로 저장.

ResponseEntity.badRequest()로 JSON 형태의 응답을 반환.

 */