package interceptor.interceptorex;

import interceptor.annotation.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();

        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())
                .build().toUri();
        // 서버의 uri뿐 만아니라 query까지 정보를 담아서 uri에 저장한다.

        log.info("request url : {}", url);

        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        // handler 변수에는 다루는 서버의 class가 담겨있다.
        log.info("has annotation : {}", hasAnnotation);
        // -> public 인 경우에는 false가 나오고 private는 true가 나온다.

        // 모든 서버가 public으로 동작을 하지만
        // Auth를 가지고 있는 요청에 대해서는 세션, 쿠키 등등을 봐야한다.

        if(hasAnnotation){  // Auth를 가지고 있는지 확인
         String query = uri.getQuery();
         // uri에 있는 정보중 query값만 뺴내서 String 변수에 저장장
        log.info("query : {}", query);

         if(query.equals("name=steve")){
             return true;
            }
            return false;
        }

        return true;
        // 최종 return 값이 true이기 떄문에 일단 실행은 된다.
        // 하지만 Auth 어노테이션을 가지고 있지 않으면 if문을 통과할수 없기 떄문에 body에 아무런 정보가 입력이 되지 않는다.
    }

    private boolean checkAnnotation(Object handler, Class clazz){
        // resource, js, html 등등은 true로 통과
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        // annotation check(달려 있는가)
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            // Auth annotation이 있다면 true
            return true;
        }

        return false;
    }
}
