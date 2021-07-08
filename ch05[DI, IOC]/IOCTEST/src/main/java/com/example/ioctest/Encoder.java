package com.example.ioctest;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


//@Component
public class Encoder {

    private IEncoder iEncoder;

//    public Encoder(@Qualifier("base64Encoder") IEncoder iencoder){
//        this.iEncoder = iencoder;
//    }

    public Encoder( IEncoder iencoder){
        this.iEncoder = iencoder;
    }

    public void setIencoder(IEncoder iencoder){
        this.iEncoder = iencoder;
    }

    public String encode(String message){

        return iEncoder.encode(message);
    }

    // 한개만 있을떄에는 바로 매칭을 해주어서 문제가 없지만
    // class가 두개가 되는 경우에는 프로그램 내에서 결정을 못하게 된다.
    // 그럴떄에 Qualifier를 이용해 지정을 해주면 된다.
    // 만약 Base64Encoder를 지정해준다면(지정할떄는 모두 소문자를 사용함)
    // 만일 직접 Bean을 지정하여 생성하는 경우에는 Component가 필요가 없다.

}
