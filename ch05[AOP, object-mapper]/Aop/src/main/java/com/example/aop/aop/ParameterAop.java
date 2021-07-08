package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void Cut(){}

    @Before("Cut()") // Pointcut이 실행되는 지점 전에 이걸 실행하겠다
    public void before(JoinPoint joinPoint){
        // Method의 이름까지 불러올수가 있다.
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args = joinPoint.getArgs(); // 메서드에 들어가는 매개변수들의 배열
        for(Object obj : args){
            System.out.println("type : "+obj.getClass().getSimpleName());
            System.out.println("value : "+obj);
        }
    }

    @AfterReturning(value = "Cut()", returning = "obj") // 메서드가 실행되고 나면 해당 obj값을 여기서 볼수가 있다.
    public void afterReturn(JoinPoint joinPoint, Object obj){
        //AfterReturning 는 Object객체를 받을수 있다.
        System.out.println("return obj");
        System.out.println(obj);
    }

}

// @Aspect : aop로 작동을 하라는 어노테이션
// Spring에서 관리가 되어야 하기 떄문에 component로 추가
// @Pointcut 어노테이션에는 정말 많은 기능이 있기 떄문에 대표적인것만 살펴볼 예정

// @Pointcut() : ()안에는 굉장히 많은것들이 들어갈수 있기 떄문에 찾아보면서 공부해야한다.
// 위에 저 내용은 aop에 있는 controller의 패키지 하위에 있는 메서드를 aop로 보겠다는 의미
// 정보가 많지만 사용법은 거의 비슷하기 하다.

// JoinPoint 들어가는 지점에 대한 정보를 가지고 있는 객체

// 만약 postMapping을 실행하게 되면
// before -> postMapping -> After 순으로 실행이 된다.
