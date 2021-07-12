package com.example.ch62.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/api/user/*") // 특정한 클래스에 적용하는 어노테이션
// @Component 모든것에 사용할떄에는 이걸 사용
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 전처리

//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);
        // 전처리

        chain.doFilter(httpServletRequest, httpServletResponse); // doFilter이 기준점

        // 후처리
        String url = httpServletRequest.getRequestURI();
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("request url : {}, request body : {}", url, reqContent);


        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStauts = httpServletResponse.getStatus();
        log.info("response status : {}, responseBodt : {}", httpStauts, resContent);

        httpServletResponse.copyBodyToResponse();
        // 윗 코드를 실행하고 나면 정보가 읽히고 난후기 떄문에
        // 더이상 정보가 없다(쓰레드와 비슷하다고 보면됨)
        // 그러기 떄문에 Body에 정보를 출력하기 위해서 정보를 복사하는 코드를 추가해준다.
    }
}


// HttpServletRequest로 변환하여 사용하게 된다면
// 필터 처리에서 정보를 한번 읽어버리기 떄문에 Spring에서는 읽을 데이터가 없게 된다.
// -> 오류가 발생
// 이를 해결하기 위해서 ContentCachingRequestWrapper를 사용

// ContentCachingRequestWrapper 처음 이것이 사용되었을떄는 정보를 담지는 않고 길이만을 초기화 한다.
// 실제 내부 Spring으로 들어가야 메서드가 실행이 되면서 정보가 담기게 되고 그래야 우리가 정보를 읽을수가 있다.
// -> 반드시 doFilter이후에 읽어야 한다.