package com.example.exception.advice;


import com.example.exception.controller.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice()
// ()안에 package를 추가해줌으로써 특정한 package에서만 작동하게 설정할수 있다.
// REST API를 사용하기 떄문에
public class GlobalApiControllerAdivice {
    // 어떤 예외를 잡을지 지정

    // 잡을 예외를 지정 해준다.
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){

        // 어떤 예외가 발생했는지 출력해주는 코드
        System.out.println(e.getClass().getName());
        //오류를 출력해줌
        System.out.println(e.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        //  서버에서 일어나는 예외이기 떄문에 Internal로 설정해 준다.
        // 에러 발생시 body에 아무것도 입력을 하지 않는다(서버 에러로 리턴)
    }

    // 윗 코딩은 모든 에러를 잡는거지만 아래 코딩은 특정 에러를 잡을떄 사용
    @ExceptionHandler(value =  MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

// controller가 여러개 있어도 RestControllerAdvice는 모든 controller에 적용되어서 에러를 잡는다.

// 만약 특정한 controllr에 지정하고 싶다면 @ExceptionHandler를 지정하고자 하는 CONTROLLER에 넣어주면 된다.
// 우선순위는 controller에 지정한 에러가 위다.