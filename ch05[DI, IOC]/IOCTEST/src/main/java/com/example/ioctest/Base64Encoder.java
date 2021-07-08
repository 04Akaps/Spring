package com.example.ioctest;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
// Spring으로 관리를 해달라는 어노테이션
// 사용시 자동으로 main도 관리가 된다.
// 이걸 사용하면 Spring가 실행이 될떄 객체를 싱글톤 형태로 만들어서 관리를 하게 된다.
public class Base64Encoder implements IEncoder{


    public String encode(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
