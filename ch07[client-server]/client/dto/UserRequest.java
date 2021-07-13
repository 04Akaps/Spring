package com.example.client.dto;

public class UserRequest {

    private String name;
    private int age;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
