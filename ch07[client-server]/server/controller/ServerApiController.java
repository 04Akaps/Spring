package com.example.server.controller;


import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/hello")
    public User hello(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String address){
        User user = new User();
        user.setAddress(address);
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @PostMapping("/user/{age}/name/{userName}/address/{address}")
    public User post(@RequestBody User user,
                     @PathVariable int age,
                     @PathVariable String userName,
                     @PathVariable String address){
        log.info("age : {}, userName : {} , address : {} ",age, userName, address );
        log.info("client req : {}", user);
        return user;
    }

    @PostMapping("/user/{age}/name/{userName}")
    public User post2(@RequestBody User user,
                     @PathVariable int age,
                     @PathVariable String userName,
                      @RequestHeader("X-authorization") String authorization,
                      @RequestHeader("custom-header") String customHeader){
        log.info("age : {}, userName : {}",age, userName );
        log.info("authorization : {}, customHeader : {} ",authorization, customHeader);
        log.info("client req : {}", user);
        return user;
    }
    // post 메서드와 구별해주기 위해서 address를 뻈다.

    @PostMapping("/user/{age}")
    public Req<User> post3(
            HttpEntity<String> entity,
            @RequestBody Req<User> user,
            @PathVariable int age,
            @RequestHeader("X-authorization") String authorization,
            @RequestHeader("custom-header") String customHeader){
        log.info("req : {}", entity.getBody());
        log.info("age : {}",age  );
        log.info("authorization : {}, customHeader : {} ",authorization, customHeader);
        log.info("client req : {}", user);

        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header()
        );
        response.setResBody(
                user.getResBody()
        );


        return response;
    }

}
