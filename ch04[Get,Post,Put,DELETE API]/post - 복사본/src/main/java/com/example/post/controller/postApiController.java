package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class postApiController {

    @PostMapping("/post")
    public void post(@RequestBody Map<String, Object> requestData) {
        requestData.entrySet().forEach(e -> {
            System.out.println("key : " + e.getKey());
            System.out.println("key : " + e.getValue());
        });
    }
    //post 경우에는 @RequestBody를 붙여 주어야 한다.

    @PostMapping("/post2")
    public void post2(@RequestBody PostRequestDto a) {
        System.out.println(a);
    }

    //pathVariable 같은 경우에도 get방식과 동일하게 사용하면 된다.
}


