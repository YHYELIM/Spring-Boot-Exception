package com.example.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping(path="")
    public void hello(){
        var list = List.of("hello");//[hello]
        var element = list.get(1);//index 0 만 유효

        log.info("element : {}", element);
       // throw new RuntimeException("run time exception call");
    }




}
