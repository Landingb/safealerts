package com.safetynet.alerts.model.url;

import java.util.List;

import com.safetynet.alerts.model.Person;

public class ChildInfo {
    String firstName;
    String lastName;
    int age;
    List<Person> houseMembers;
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lasName) {
        this.lastName = lasName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public List<Person> getHouseMembers() {
        return houseMembers;
    }
    public void setHouseMembers(List<Person> houseMembers) {
        this.houseMembers = houseMembers;
    }

}
