package com.example.validation2.dto;

import com.example.validation2.annotation.YearMonth;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User2 {
    @NotBlank
    private String name;

    @Min(value = 0)
    private int age;

    @Email
    private String email;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번흐의 양식과 맞지 않습니다.")
    private String phoneNumber;

    //@YearMonth(pattern = "yyyyMM") -> 기본 패턴을 적용하지 않았을떄
    @YearMonth //기본패턴을 적용했을떄
    private String reqYearMonth; //yyyymm

    public String getReqYearMonth() {
        return reqYearMonth;
    }

    public void setReqYearMonth(String reqYearMonth) {
        this.reqYearMonth = reqYearMonth;
    }

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

//    @AssertTrue // return이 true면 정상
//    public boolean isreqYearMonthValidaition(){
//        //boolean 메서드 사용시에는 메서드명에 is가 들어가야 한다.
//        try{
//            LocalDate localDate = LocalDate.parse(getReqYearMonth()+"01", DateTimeFormatter.ofPattern("yyyyMM"));
//        }catch(Exception e){
//            return false;
//        }
//        return true;
//    }
    //-> 어노테이션을 직접 만들어서 사용안할떄는 이렇게 사용해야한다.
    // -> 대신 재활용 불가능

    @Override
    public String toString() {
        return "User2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reqYearMonth='" + reqYearMonth + '\'' +
                '}';
    }
}

// AssertTure 코드들은 재활용이 불가능하다.
// -> 다른 클래스에서 같은 역할을 하는 코드를 사용하려면
// -> 계속해서 클래스마다 만들어 주어야 한다.

// 재활용을 하려면 직접 어노테이션을 만들어 주어야 한다.



