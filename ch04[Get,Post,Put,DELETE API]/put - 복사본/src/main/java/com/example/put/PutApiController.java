package com.example.put;

import com.example.put.dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PutApiController {

    @PutMapping("/put")
    public void put(@RequestBody PostRequestDto requestDto){
         System.out.println(requestDto);
    }

    @PutMapping("/put/{userId}")
    public void put2(@RequestBody PostRequestDto requestDto, @PathVariable(name = "userId") Long id){
        System.out.println(id);
    }

    // @RequestBody화 @PathVariable은 동시에 사용할수 있다.
    // RequestBody는 말그대로 Body를 구성해 입력된 값을 출력하고
    // PathVariable는 url에 직접적으로 값을 입력해 유동적으로 값을 입력받을수 있다.
}
