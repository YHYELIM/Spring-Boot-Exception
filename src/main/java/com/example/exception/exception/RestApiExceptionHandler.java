package com.example.exception.exception;

import com.example.exception.controller.RestApiBController;
import com.example.exception.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice(basePackageClasses = {RestController.class, RestApiBController.class})
//해당 클래스는 RestApi가 사용하는곳에 예외가 일어나는 곳을 감지
@Order(1)// 양수에 가까울수록 먼저 처리
public class RestApiExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Api> exception (
            Exception e
    ){
        log.error("",e);

        var response = Api.builder()
                .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .resultMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }



    @ExceptionHandler(value = {IndexOutOfBoundsException.class}) // value ={예외를 잡을 해당 클래스}
    public ResponseEntity outOfBound(IndexOutOfBoundsException e){// 해당 예외 클래스 받음
        log.error("IndexOutOfBoundsException", e);

        return ResponseEntity.status(200).build();
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Api> noSuchElement(NoSuchElementException e){
        log.error("",e);
        //리소스가 없는 404 에러코드


        var respose = Api.builder()
                .resultCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .resultMessage(HttpStatus.NOT_FOUND.name())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(respose);
        //ResponseEntity로 한번 감싼 Api 내리기
    }
}
