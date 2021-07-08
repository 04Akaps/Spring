package com.company.ioc;

public class Main {

    public static void main(String[] args) {
	    String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

	    // Base 64 encoding
        Encoder encoder = new Encoder(new Base64Encoder()); // 이것을 DI라고 한다.
        String result = encoder.encode(url);
        System.out.println(result);

        // 내가 이해한 바를 정리해 보자면
        // DI는 코드를 리팩토링 하는것과 같다고 생각한다.
        // 복잡한 코드를 단순하게 만들어 준뒤 조금의 수정을 통해서 다양한 Test를 할수 있게 만드는것

        // -> 실제로도 수업 내용이 urlEncoder, Base64를 각각 생성자로 받아서 코드를 수행하다가.
        // -> interface로 상속을 시켜준뒤 하나의 class를 통해서 수정가능하게 만들었다.


    }
}
