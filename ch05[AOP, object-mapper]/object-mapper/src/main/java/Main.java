import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String args[]) throws JsonProcessingException {
        System.out.println("main");

        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        user.setName("홍길동");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22가 2222");
        car2.setType("SUV");

        List<Car> list = Arrays.asList(car1, car2);
//      List<Car> list2 = new ArrayList<>();
//      list2.add(car1);
//      list2.add(car2)
//      위 코드를 줄인것
        user.setCars(list);


        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        // 직접 노드를 건들어야 할떄에 사용한다.
        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();

        // 배열을 가져오는 방법
        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode)cars; // 타입 변환
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});

        // name을 steve로 age를 20으로 바꾸는 코드
        ObjectNode objectNode = (ObjectNode) jsonNode; //json노드를 바꾸기 위해서 형변환
        objectNode.put("name", "steve");
        objectNode.put("age", 20);


        // window 환경일 경우에는 setting 을 utf-8로 바꿔주어야 한다.

        // 자세한 수정사항은 복잡하기 떄문에 필요할시 수업 내용을 참고할것
        // JAVA프로젝트를 할떄에만 수정하면 된다
        // Spring 프로젝트는 기본적으로 UTF-8이다.

        // 반드시 encoding은 utf-8로 해야한다!!!!!!!!!!!!

        //convertValue : map 및 object등을 원하는 class로 맵핑시킬수 있다.
        // asText, asInt : 형변환을 해주는 코드

        // 특정 json노드의 값을 바꿀수가 있다.
        // JsonNode에서는 set을 막아놓았기 떄문에 다른 방법을 활용해야 한다.

    }
}


