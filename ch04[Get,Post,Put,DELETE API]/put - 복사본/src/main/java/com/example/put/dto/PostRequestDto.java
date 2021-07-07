package com.example.put.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
//JsonNaming 을 통해서 전체를 스네이크 텍스트?를 처리할것인지
// 아니면 JsonProperty를 통해서 특정 변수만 네이크트 텍스트 처리를 할것인지 선택할수 있다.
// 두 에노테이션을 사용하게 되면 반드시 스네이크 텍스트? 로 입력을 해주어야 한다.
public class PostRequestDto {

    private String name;
    private String age;
    private List<CarDto> carList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<CarDto> getCarList() {
        return carList;
    }

    public void setCarList(List<CarDto> carList) {
        this.carList = carList;
    }

    @Override
    public String toString() {
        return "PostRequestDto{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", carList=" + carList +
                '}';
    }
}
