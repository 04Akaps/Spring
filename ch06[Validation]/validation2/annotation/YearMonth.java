package com.example.validation2.annotation;


import com.example.validation2.validator.YearMonthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// 어떠한 클래스를 검사할지 지정
@Constraint(validatedBy = {YearMonthValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface YearMonth {

    String message() default "yyyyMM에 맞지 않습니다.";
    // -> 잘못되었을떄 호출할 문자열
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    // 여기 위에 코드는 기존의 @Email에 있는 메서드를 가져온것


    String pattern() default "yyyyMMdd";
    // -> 기본 패턴을 지정(default ""를 추가)



}
