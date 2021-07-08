package com.example.ioctest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class IoctestApplication {

    public static void main(String[] args) {
        SpringApplication.run(IoctestApplication.class, args);
        //Spring 이 실행이 된다.
        ApplicationContext context = ApplicationContextProvider.getContext();
        // Spring이 실행이 되고 나면 값을 가져 온다.
        //  ApplicationContextProvider.getContext();  : Static이기 떄문에 바로 사용 가능

       // Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
      //  UrlEncoder urlEencoder = context.getBean(UrlEncoder.class);
        // 객체 관리는 new로 하지 않는다.(bean으로 관리)
        // 위에 있는 코드를 통해서 context에 값을 저장하였기 떄문에 context를 통해서 @Componenet 한 class들의
        // 객체를 불러 올수가 있다.
        // Encoder도 component 처리하게 된다면 저렇게 따로 불러오지 않아도 된다.


        Encoder encoder = context.getBean("base64Encode", Encoder.class);
       // Encoder encoder = context.getBean("urlEncode", Encoder.class);
        String url = "www naver.com";

        String result = encoder.encode(url);
        System.out.println(result);

        // 이렇게 모두 Spring에서 관리를 하게 되면 코드에 new가 보이지 않게 된다.
        // Spring에서 관리하는 모든 객체들을 Bean이라고 부른다.
    }

}

@Configuration
class AppConfig{

    @Bean("base64Encode")
    public Encoder encoder(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }

    @Bean("urlEncode")
    public Encoder encoder(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }
}
// 이처럼 직접 Bean을 지정해 줄수도 있다.
// 하지만 둘다 encoder로 구분이 불가능하기 떄문에 Bean어노테이션에 이름을 지정해 준다
// 이름 지정해주는것은 Component에서도 똑같이 적용 가능
// ** 하지만 기존 class와 이름이 같다면 충돌이 일어나게 된다. **

