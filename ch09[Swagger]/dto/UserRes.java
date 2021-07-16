package com.example.swagger.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRes {
    @ApiModelProperty(value = "사용자의 이름", example = "steve", required = true)
    private String name;
    @ApiModelProperty(value = "사용자의 나이", example = "10", required = true)
    private int age;

    // @ApiModelProperty : @ApiParam과 비슷한 역할
    // 마찬가지로 객체의 변수에 제목을 달아주고 예시같은것 또한 표기해줄수 있다.
}
