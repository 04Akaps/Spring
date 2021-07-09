package com.example.exception.controller;


import com.example.exception.dto.User3;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class ApiController {

    @GetMapping("")
    public User3 get(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
        User3 user3 = new User3();
        user3.setAge(age);
        user3.setName(name);

        // setAge의 변수에는 int 타입을 받아야 하는데 Integer타입이 들어오니 에러가 뜸

        return user3;
    }

    @PostMapping("")
    public User3 post(@Valid @RequestBody User3 user){

        return  user;
    }

}

// (required = false) : 꼭 필수가 아니다.
// -> 없어도 동작이 되지만 없다면 null이 된다.
// -> true라면 반드시 값이 있어야 한다.

// int와 integer은 다르기 떄문에 age가 오류가 뜬다.

// @Valid를 추가해주지 않는다면 Validation 어노테이션의 제약에 걸리지 않는다.
// Get는 제약이 없고 , post는 제약이 있다.

