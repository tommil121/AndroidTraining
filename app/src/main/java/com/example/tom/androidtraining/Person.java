package com.example.tom.androidtraining;

/**
 * Created by Tom on 10/22/2015.
 */
public class Person {

    private int id;
    private String name;
    private String email;
    private String address;
    private String city;

    public Person(){

    }

    public Person(String address, String city, String email, String name){
        this.address = address;
        this.city = city;
        this.email = email;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
