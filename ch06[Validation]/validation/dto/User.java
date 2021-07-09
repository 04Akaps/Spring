package com.example.validation.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class User {
    @NotBlank
    private String name;

    @Min(value = 0)
    private int age;

    @Email
    private String email;
    //@Email 형식이 아니라면 에러가 발생(sdl155@Naver.com 같은 형식만 가능)

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번흐의 양식과 맞지 않습니다.")
    private String phoneNumber;
    // Pattern : 정규식을 사용하는 어노테이션(010-aaaa-bbbb 같은 형식만 가능)
    // 정규식은 단순하게 인터넷에 검색하면됨(굳이 공부하지 않아도 된다.)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumer(String phoneNumer) {
        this.phoneNumber = phoneNumer;
    }

    @Override
    public String toString() {
        return "User2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumer='" + phoneNumber + '\'' +
                '}';
    }
}
