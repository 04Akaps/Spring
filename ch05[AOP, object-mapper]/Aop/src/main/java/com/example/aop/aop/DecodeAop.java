package com.example.aop.aop;


import com.example.aop.dto.User;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void Cut(){}

    @Pointcut("@annotation(com.example.aop.annotation.Decode)")
    // Timer어노테이션이 적용된 메서드만 로깅하겠다는 의미
    private void enableDecode(){}

    @Before("Cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof User) {
                User user = User.class.cast(arg);  // 형변화를 시킨다.
                String base64Email = user.getEmail();

                String email = new String(Base64.getDecoder().decode(base64Email), "UTF-8");
                //decode의 반환형이 byte이기 떄문에 new String을 통해서 String으로 만들어 준다.
                // decoding 하는 코드
                user.setEmail(email);
            }
        }
    }

    @AfterReturning(value = "Cut() && enableDecode()", returning = "obj")
    public  void afterReturn(JoinPoint joinPoint, Object obj){
        if(obj  instanceof  User){
            User user = User.class.cast(obj);  // 형변화를 시킨다.
            String email = user.getEmail();
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes());
            user.setEmail(base64Email);
        }
        // 위쪽 메서드와 완전 반대되는 과정이다다
    }
}

// 전은 디코드를 해서 들어오고
// 전송 후는 인코딩을 해서 내보낸다.

//Around를 사용하면 사용 전후를 똑같은 코드로 작동 시킬수 있지만
// 따로따로 지정해 주면 다른 방식으로 작동하게 할수가 있다.
