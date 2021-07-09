package com.example.validation2.controller;

import com.example.validation2.dto.User2;
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
public class ApiController2 {

//    @PostMapping("/user")
//    public User2 user(@Valid @RequestBody User2 user2){
//        System.out.println(user2);
//        return user2;
//    }

    @PostMapping("/user")
    public ResponseEntity user2(@Valid @RequestBody User2 user2, BindingResult bindingResult){

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

        return ResponseEntity.ok(user2);
    }
}



