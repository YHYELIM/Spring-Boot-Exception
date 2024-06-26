package com.example.exception.exception;

import com.example.exception.controller.RestApiBController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice(basePackageClasses = {RestController.class, RestApiBController.class})
//해당 클래스는 RestApi가 사용하는곳에 예외가 일어나는 곳을 감지
public class RestApiExceptionHandler {

    @ExceptionHandler(value = {Exception.class}) // value ={예외를 잡을 해당 클래스} Exception을 상속받은 모든 예외
    public ResponseEntity exception(Exception e ){ // 해당 예외 클래스 받음
       log.error("RestApiExceptionHandler", e);
       return ResponseEntity.status(200).build();
       //예외가 발생하면 반환
       // 바디에 아무것도 넣지 않고 build
    }

    @ExceptionHandler(value = {IndexOutOfBoundsException.class}) // value ={예외를 잡을 해당 클래스}
    public ResponseEntity outOfBound(IndexOutOfBoundsException e){// 해당 예외 클래스 받음
        log.error("IndexOutOfBoundsException", e);

        return ResponseEntity.status(200).build();
    }
}
