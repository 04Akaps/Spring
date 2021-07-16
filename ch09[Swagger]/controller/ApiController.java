package com.example.swagger.controller;


import com.example.swagger.dto.UserReq;
import com.example.swagger.dto.UserRes;
import io.swagger.annotations.*;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"API 정보를 제공하는 Controller"})
// http://localhost:8080/swagger-ui/ 롤 통해 이동시 제목을 정해주는 어노테이션
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public String hello(){
        return"hello";
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "x", value = "x 값"),
//            @ApiImplicitParam(name = "y", value = "y 값")
//     })
    // ApiParm 으로 하나씩 지정해주는것도 가능하지만
    // 이처럼 다른 공간을 만들어서 제목만 관리해주는것도 가능하다.
    // ApiImplicitParams 사용시 ApiParam은 사용 안해도 된다.
    @GetMapping("/plus/{x}")
    public int plus(@ApiParam(value = "x값") // 이름을 부여해준다(변수에)
                    @PathVariable int x,

                    @ApiParam(value = "y값")
                    @RequestParam int y){
        return x+y;
    }

    @ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일떄")
    @ApiOperation(value = "사용자의 이름과 나이를 리턴 하는 메소드")
    @GetMapping("/user")
    public UserRes user(UserReq user){
        return new UserRes(user.getName(), user.getAge());
    }

    @PostMapping("/user")
    public UserRes user2(@RequestBody UserReq user){
        return new UserRes(user.getName(), user.getAge());
    }

}

