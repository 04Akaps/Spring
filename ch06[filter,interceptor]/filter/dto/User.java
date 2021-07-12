package com.example.ch62.dto;


import lombok.*;


// @Getter             // get 메서드
// @Setter             // set 메서드
@Data               // get, set, ToString 포함
@NoArgsConstructor      // 기본 생성자를 의미
@AllArgsConstructor     // 전체 생성자(모든것을 생성)
public class User {

    private String name;
    private int age;
}

// 롬북을 활용하면 코딩이 보기 편하고 깔끔해 진다.
// 롬북을 활용하면 변수만을 생성하면 된다.
