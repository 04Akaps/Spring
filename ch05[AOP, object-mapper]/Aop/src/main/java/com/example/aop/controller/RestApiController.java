package com.example.aop.controller;

import com.example.aop.annotation.Decode;
import com.example.aop.annotation.Timer;
import com.example.aop.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class RestApiController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name){
//        System.out.println("get method");
//        System.out.println("get method" +id);
//        System.out.println("get method" +name);
        return id+" "+name;
    }
    @PostMapping("/post")
    public User post(@RequestBody User user){
        System.out.println("psost method : " +user);
        return user;
    }

    @Timer  // 직접 만든 어노테이션
    @DeleteMapping("/delete")
    public void delete() {

        try {
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 임의로 2초를 설정해준것
    }

    @Decode
    @PutMapping("/put")
    public User put(@RequestBody User user){
        System.out.println("put");
        System.out.println(user);
        return user;
    }

    // 실무에서는 mapping 이 많기 떄문에 각 메서드를 한곳으로 몰아주어야 한다 = aop
    // aop를 활용하면 굉장히 많은 log가 남게 되지만
    // 중요한 부분에 로그를 남기는데에는 매우 유용하다.

    // Befor에 method의 이름까지 출력하는 코드를 추가하면
    // 따로 sysout 를 추가하지 않아도 로그에 표기가 남는다.
    // 실제로 실무에서는 sysout를 추가해놓지 않고 메소드의 이름까지 출력해
    // 로그의 전체적인 모습을 확인한다.
}
