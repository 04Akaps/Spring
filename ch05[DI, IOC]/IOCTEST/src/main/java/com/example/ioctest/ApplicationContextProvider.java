package com.example.ioctest;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

// container에 저장된 객체를 꺼내오는 class
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    //Spring이 실행될떄떄 context에 값을 입력받는다.
    //이후 그냥 사용만 하면 된다.
}
