package com.example.response.controller;

import com.example.response.controller.Dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//페이지를 리턴하는 서버
@Controller
public class PageController {

    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }
    //return이 String 이면 자동으로 return타입에 적은 값을 찾아간다.
    // Controller의 역학

    @ResponseBody
    @GetMapping("/user")
    public User user(){
        var user = new User();
        // var은 타입을 추론해주는 타입

        user.setName("steve");
        user.setAddress("세병로90");
        return user;
    }
    //ResponseBody를 추가해주면 return하면 resource를 찾지않고
    // 말그대로 body를 내려주겠다는 의미
    // User클래스에 있는 모든 변수의 값을 입력하지 않는다면
    // 입력한 값들만 출력이되고 입력이 없는 값들은 0, null로 내려오게 된다.
    // 보통 페이지에 값을 입력하는 클래스에서는 ResponseBody를 사용하지 않는게 맞다.
    // -> API컨트롤러에서 입력하는것이 정확한 방법이다.

}
