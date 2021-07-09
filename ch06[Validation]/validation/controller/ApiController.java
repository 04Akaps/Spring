package com.example.validation.controller;

import com.example.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

//    @PostMapping("/user")
//    public User user(@Valid @RequestBody User user){
//        System.out.println(user);
//        return user;
//    }

    @PostMapping("/user")
    public ResponseEntity user2(@Valid @RequestBody User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(e ->{
                FieldError field = (FieldError) e;
                String message = e.getDefaultMessage();

                System.out.println(field.getField());
                System.out.println(message);

                sb.append(field.getField());
                sb.append(message);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }
        // 에러가 발생시에 에러를 가져오는 코드
        // 후에 예외처리를 배울꺼기 떄문에 이런 방법도 있다고 알고만 있자

        return ResponseEntity.ok(user);
    }
}


// @Valid : 받는 객체의 변수들을 검사를 해서 맞는 값만 보내중
// -> 쉽게 말하면 그냥 Validation을 사용하려면 쓰라는 소리
// -> Validaton의 어노테이션을 사용하는 객체를 받을떄에는 반드시 @Valid를 추가해 주어야 한다.

// BindingResult : 에러가 떴을떄 그 값이 들어오게 된다.
// message : 오류가 떴을떄 출력해줄 문구
// ResponseEntity :  사용자의 응답 데이터를 포함하는 클래스
// BindingResult : Valid를 사용하는 클래스에서 객체값을 검증하는 인터페이스

