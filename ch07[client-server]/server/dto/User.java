package com.example.server.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 롬북을 사용하기 있기 떄문에 사용 가능
// client에서는 사용 불가능
public class User {
    private String name;
    private int age;
    private String address;
}
