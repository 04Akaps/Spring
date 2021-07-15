package com.example.springcalculator.controller;


import com.example.springcalculator.component.Calculator;
import com.example.springcalculator.component.DollarCalculator;
import com.example.springcalculator.component.MarKetApi;
import com.example.springcalculator.dto.Req;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CalculatorApiController.class)
// SpringBootTest같은 경우에는 모든Bean을 호출하지만
// WebMvcTest같은경우에는 Web동작에 필요한 것들만 호출하기 떄문에 자원을 아낄수 있다.
@AutoConfigureWebMvc
@Import({Calculator.class, DollarCalculator.class})
// CalculatorApiController에서 Calculator을 주입받고 있고 Calculator에서 Icalculator을 주입받고 있기 떄문에
// 둘다 추가해 준다.(Icalculator에서 DollarCalculator를 사용하기 떄문에)
// DollarCalculator에서는 MarketApi를 주입받고 있는데 이것은 모킹 처리해준다.
public class CalculatorApiControllerTest {

    @MockBean
    private MarKetApi marKetApi;

    @Autowired
    private MockMvc mockMvc;
    // 사용하기 위해서 단순히 꺼내주는것임

    @BeforeEach
    public void init(){
        Mockito.when(marKetApi.connect()).thenReturn(3000);
    }

    @Test
    public void sumTest() throws Exception {
        // http://localhost:8080/api/sum
        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                        .queryParam("x", "10")
                        .queryParam("y","10")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("60000")
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void minusTest() throws Exception {
        Req req = new Req();
        req.setX(10);
        req.setY(10);

        String json = new ObjectMapper().writeValueAsString(req);

        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8080/api/minus2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.result").value("0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
        ).andDo(MockMvcResultHandlers.print());


    }
}
