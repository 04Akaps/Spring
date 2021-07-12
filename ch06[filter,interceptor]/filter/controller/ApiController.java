package com.example.ch62.controller;


import com.example.ch62.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j  // System.out 으로 찍는것이 아니라 로그를 사용할수 있게 된다.
@RestController
@RequestMapping("/api/user")
public class ApiController {

    @PostMapping
    public User user(@RequestBody User user){
        log.info("User : {}",user);
        // {}에 user란 값을 넣어주는 방식
        return user;
    }
}


// JAVA, Spring 을 이용하는 회사라면 기본적으로 롬북을 사용한다(안쓰는 회사는 없다)