package com.example.client.service;


import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {
    // http://localhost/api/server/hello
    // response
    public UserResponse Get(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name", "steve")  // 주소 뒤에 query를 넣는 방법
                .queryParam("age", "10")
                .queryParam("address", "hojin")
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
        // 상세한 정보를 알기 위해서는 ResponseEntity로 받는것을 추천
        // result에는 getStatusCode(), getBody() 등등 많은것이 담겨 있다.

        // String result = restTemplate.getForObject(uri, String.class);
        // -> server의 return 값이 String이기 떄문에 String으로 받는것이다.
        // String이 아닌 객체를 받고자 한다면
        // UserResponse result = restTemplate.getForObject(uri, UserResponse.class);

        // hello 메서드를 통해 server에 요청을해서 server에서 응답을 해주는 코드이다.
        return result.getBody();
    }

    public UserResponse post(){
        // http://localhost:9090/api/werver/user/{userId}/name/{userName}     -> post주소
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{age}/name/{userName}/address/{address}")
                .encode()
                .build()
                .expand(100,"steve","hojin")
                .toUri();
        // expand = path에 있는 userId, userName에 순서대로 값을 넣어줄수 있다.
        System.out.println(uri);

        // htpp body 필요 :post이기 떄문에
        UserRequest req = new UserRequest();
        req.setAge(10);
        req.setName("STEVE");
        req.setAddress("hojin");
    // object로 만들어도 자동으로 json으로 바꾸어 준다.

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> respone = restTemplate.postForEntity(uri, req, UserResponse.class);

        return respone.getBody();
        //        UserRequest, UserResponse는 단순히 입력한 값과 그에따른 호출값을 보기 위함이다.
//        UserRequest에 입력한 값이 uri를 타고 server에 전송이 되고 그것을 UserResponse는 에 담게 된다.
//        이후 다시 ResponseEntity에 담아서 return함으로써 body에 값이 입력이 되는것이다.
    }

    public UserResponse exchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{age}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")
                .toUri();

        System.out.println(uri);

        UserRequest req = new UserRequest();
        req.setAge(10);
        req.setName("STEVE");

        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);

        return response.getBody();
    }

    public Req<UserResponse> genericExchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{age}")
                .encode()
                .build()
                .expand(100,"steve")
                .toUri();

        System.out.println(uri);

        UserRequest userRequest = new UserRequest();
        userRequest.setAge(10);
        userRequest.setName("STEVE");

        Req<UserRequest> req = new Req<>();
        req.setHeader(
            new Req.Header()

        );

        req.setResBody(
                userRequest

        );

        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Req<UserResponse>> response
                = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>(){});

        return response.getBody();
    }

}

