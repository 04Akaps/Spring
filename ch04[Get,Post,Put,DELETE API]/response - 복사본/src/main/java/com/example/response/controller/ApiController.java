package com.example.response.controller;

import com.example.response.controller.Dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ResourceBundle;

@RestController
@RequestMapping("/api")
public class ApiController {
    // TEXT
    @GetMapping("/text")
    public String test(@RequestParam String account){
        return account;
    }

    //JSON
    // req -> object mapper -> object -> method -> object -> object mapper -> jason -> response [ 순서 ]
    @PostMapping("/json")
    public User json(@RequestBody User user){
        return user; // 201k
    }


    // ResponseEntity
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user){

        return ResponseEntity.status(HttpStatus.CREATED).body(user); // 200k

        //put 은 보통 201을 내려주게 되지만
        //강제로 200k로 내려주는 코드
        // http의 상태를 의미한다.
        // 200 0k는 성공적으로 요청이 되었다는 소리이며
        // 201은 요청이 성공적이며 결과로 새로운 리소스가 생성되었다는 소리이다.
        // -> 일반적으로 post, put 을 사용하면 201이 입력된다.
    }
}
