package interceptor.config;


import interceptor.interceptorex.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor // 상수 변수들을 생성자를 통해 값을 입력받을수 있게 만들어 준다.
public class MvcConfig implements WebMvcConfigurer {


    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(authInterceptor);
        // registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*");
        // 이런 방식으로 따로 uri를 받아서 if문을 사용하지 않고도 addPathPatterns에 target를 입력해 줌으로써 원하는
        // 곳에서만 사용할수도 있다.(뺴는것도 가능)
    }
}
