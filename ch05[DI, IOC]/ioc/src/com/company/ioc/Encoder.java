package com.company.ioc;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encoder {

    private IEncoder iencoder;

    public Encoder(IEncoder iencoder){
        this.iencoder = iencoder;
    }

    public String encode(String message){
        return iencoder.encode(message);
    }
}
