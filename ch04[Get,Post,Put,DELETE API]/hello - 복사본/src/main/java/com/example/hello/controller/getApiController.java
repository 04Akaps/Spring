package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class getApiController {

    @GetMapping(path = "/hello") // http://localhost:8080/api/get/hello
    public String Hello(){
        return "get hello";
    }

    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    public String hi(){
        return "hi";
    }

    // 2번쨰 어노테이션을 합친것이 첫번쨰 GetMapping 이다.
    // 2번쨰 어노테이션은 예전에 사용 하던 방식
    // 1번쨰 어노테이션은 path를 추가해서 get으로 작동하게 만들어줌

    // http://localhost:8080/api/get/path-variable/{name}
    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable String name){
        System.out.println("PathVariable : " + name);
        return name;
    }
    // name이 변하는 주소
    // GetMapping에 선언한 name 과 매개변수의 name은 같아야 한다.
    // GetMapping : a 일떄 매개변수 : b 면 안된다(a여야 한다)

    @GetMapping("/path-variable2/{id}")
    public String pathVariable2(@PathVariable(name = "id") String pathName, String a){
        System.out.println("PathVariable2 : " + pathName);
        return pathName;
    }
    // 만약 매개변수가 여러개 일 경우에는 이렇게 코드를 수정하면 된다.


    // 쿼리는 처음 시작을 ?로 시작을 한다.
    // 이후 &연산자를 기준으로 key+value값이 오게 된다.

    //http://localhost:8080/api/get/query-param?
    @GetMapping("query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){
    // Map으로 받는 경우에는 key와 value값을 명확하게 알수가 없다.
        StringBuilder sb = new StringBuilder();
        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey() +" = " +entry.getValue() +"\n");
        });
        return sb.toString();
    }

    @GetMapping("query-param2")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age
    ){
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name +" "+email +" " + age;
    }
    /*
     Map으로 받게 된다면 뭐가 들어올지를 모른다.
     하지만 2번쨰 처럼 명시적으로 변수를 받고 싶다면 @RequestParam으로 직접 지정해 주면 된다.
     위와 같이 코딩을 하게 한다면 int 인 age에 String 값을 넣게 된다면 400 에러가 발생한다.
     400 에러 : 내가 잘못 입력한 경우 발생
     하지만 위와 같이 변수가 적으면 상관이 없지만 변수가 많아지게 된다면 dto를 사용 해야 한다.
    */

    @GetMapping("query-param3")
    public String queryParam03(UserRequest userRequest){
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }
    /*
    이와 같이 새로운 class를 만들어서 객체를 받아서 정보를 전달해주는 방법을 dto를 사용했다고 한다.
    -> 현업에서 가장 많이 사용하는 방법
    만약 adrress 라는 것이 들어오게 된다면 UserRequest에 address가 없기 떄문에
    파싱이 되지 않는다.(누락)
    */
}
