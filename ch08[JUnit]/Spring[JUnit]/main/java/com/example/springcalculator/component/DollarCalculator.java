package com.example.springcalculator.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DollarCalculator implements ICalculator{

    private int price = 1;
    private final MarKetApi marKetApi;

//    public DollarCalculator(MarKetApi marKetApi){
//        this.marKetApi = marKetApi;
//    }

    @Override
    public void init(){
        this.price = marKetApi.connect();
    }

    @Override
    public int sum(int x, int y) {
        x *= price;
        y *= price;
        return x+y;
    }

    @Override
    public int minus(int x, int y) {
        x *= price;
        y *= price;
        return x-y;
    }
}
