package com.example.springcalculator.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@SpringBootTest
// @Import({MarKetApi.class, DollarCalculator.class})
// SpringBootTest 을 사용하면 어차피 모든 Bean을 사용 가능하다.
public class DollarCalculatorTest {

    @MockBean
    // Spring에서는 Bean으로 관리가 되기 떄문에 Bean추가 ==> 아래 코드를 mocking처리하기 위한 어노테이션
    private MarKetApi marKetApi;

    @Autowired  // 스프링이 관리하고 있는 Bean을 받는 코드
    private DollarCalculator dollarCalculator;

    @Autowired  // 스프링이 관리하고 있는 Bean을 받는 코드
    private Calculator calculator;

    @Test
    public void dollarCalculatorTest(){

        Mockito.when(marKetApi.connect()).thenReturn(3000);

        dollarCalculator.init();
        Assertions.assertEquals(60000, dollarCalculator.sum(10,10));

        Assertions.assertEquals(60000, calculator.sum(10,10));
        // calculator는 메서드를 불러올떄 init을 선언하기 떄문에 이 메서드에 init가 없어도 작동을 한다.
    }
}
