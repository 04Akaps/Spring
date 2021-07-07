package com.example.delete.contoroller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable String userId, @RequestParam String account){
        System.out.println(userId);
        System.out.println(account);
    }

    // delete API 같은 경우에는 말그대로 삭제하는 역할 이기 떄문에
    // 많은 값을 받지 않는다.
    // get api와 딱 반대되는 효과를 준다.
}
