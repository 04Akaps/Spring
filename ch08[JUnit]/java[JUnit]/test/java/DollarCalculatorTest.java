import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
// 특정한 객체가 메서드를 호출하였을떄 원하는 값을 리턴해주는 것을 설정하는 어노테이션 = 모킹이라고도 한다.
public class DollarCalculatorTest {
    @Mock
    public MarKetApi marKetApi;

    @BeforeEach
    public void init(){
        Mockito.lenient().when(marKetApi.connect()).thenReturn(3000);
    }

    @Test
    public void dollarTest(){
        MarKetApi marKetApi = new MarKetApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marKetApi);
        dollarCalculator.init();
        // Mock처리한 MarKetApi 의 변수를 사용하지 않았기 떄문에 BeforeEach가 실행되지 않는다.

        Calculator calculator = new Calculator(dollarCalculator);

        Assertions.assertEquals(22000,calculator.sum(10,10));
        // Assertions.assertEquals(예측하는 결과 , 계산코드)
    }

    @Test
    public void mockTest(){
        DollarCalculator dollarCalculator = new DollarCalculator(marKetApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        Assertions.assertEquals(2000,calculator.sum(10,10));
        // Assertions.assertEquals(예측하는 결과 , 계산코드)
    }
}
