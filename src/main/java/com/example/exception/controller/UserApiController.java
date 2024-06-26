package com.example.exception.controller;


import com.example.exception.model.Api;
import com.example.exception.model.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApiController {


    //디비가 없기때문에 별도로 유저 선언
    private static List<UserResponse> userList = List.of(
            UserResponse.builder()
                    .id("1")
                    .age(10)
                    .name("홍길동")
                    .build(),


            UserResponse.builder()
                    .id("2")
                    .age(20)
                    .name("이길동")
                    .build()
    );

    @GetMapping("/id/{userId}")
    public Api<UserResponse> getUser(@PathVariable String userId){

        //response를 하기 위해서 user 찾아옴
        //get을 하게 되면 찾을 수 없기 때문에 stream 사용

        //알수없는 에러 터뜨리기
        if(true){
            throw new RuntimeException("message");
        }


        var user  = userList.stream( )
                .filter(
                        it-> it.getId().equals(userId)
                )
                .findFirst().get();
                //it : userList에 들어있는 객체들

        //user : optional한 객체
        //optional한 데이터 response
        Api<UserResponse> response  = Api.<UserResponse>builder()
                .resultCode(String.valueOf (HttpStatus.OK.value()))//value-> int 반환
                .resultMessage(HttpStatus.OK.name())
                .data(user)
                .build();

        //user데이터가 있을수도 있고 없을 수도 있기 때문에 올바른 응답 데이터를 만들어줘야함

        return response;
        //해당 유저를 찾아서 리턴해줌


    }
}
