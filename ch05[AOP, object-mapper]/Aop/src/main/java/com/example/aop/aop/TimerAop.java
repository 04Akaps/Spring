package com.example.aop.aop;


import com.example.aop.annotation.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class TimerAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void Cut(){}

    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    // Timer어노테이션이 적용된 메서드만 로깅하겠다는 의미
    private void enableTimer(){}

    @Around("Cut() && enableTimer()") // 두가지 메서드 조건을 같이 사용하겠다
    public  void around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();


        Object result = joinPoint.proceed(); // 메서드의 실행지점
        // 코드 위쪽은 시작전 아래쪽은 시작후

        stopWatch.stop();

        System.out.println("total time : " +stopWatch.getTotalTimeSeconds());
    }
}

//ProceedingJoinPoint : 개발 도중 호출되는 대상 객체에 대한 정보, 실행되는 메서드에 대한 정보 및
// 전체적인 실행될떄의 정보가 담겨있는 인터페이스로 변수를 받아 proceed를 통해 접근 가능하다.

// getTotalTimeSeconds : 입력받은 시간을 초단위로 출력해주는 메서드

