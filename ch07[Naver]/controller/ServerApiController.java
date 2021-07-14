package com.example.naver.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {
    //  https://openapi.naver.com
    //  v1/search/local.json
    // ?query=%EC%A3%BC%EC%8B%9D
    // &display=10
    // &start=1
    // &sort=random
    @GetMapping("/naver")
    public String naver() {

        String query = "갈비집";


        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("v1/search/local.json")
                .queryParam("query", "갈비집")
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode(Charset.forName("UTF-8"))       // 갈바집을 UTF-8로 인코딩 해준다.
                .build()
                .toUri();

        log.info("uri : {} ", uri);

        RestTemplate restTemplate = new RestTemplate();
        // Header
        RequestEntity<Void> req = RequestEntity // Get인 경우에는 void
                .get(uri)
                .header("X-Naver-Client-Id", "fQDJJIy93FuKMuMvYpqo")
                .header("X-Naver-Client-Secret", "gi0cy4N6Om")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        return result.getBody();
    }

}
