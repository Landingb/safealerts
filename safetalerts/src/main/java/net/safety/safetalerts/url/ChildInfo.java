package net.safety.safetalerts.url;

import net.safety.safetalerts.model.Persons;

import java.util.List;

public class ChildInfo {
    String firstName;
    String lastName;
    int age;
    List<Persons> houseMembers;


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
    public List<Persons> getHouseMembers() {
        return houseMembers;
    }
    public void setHouseMembers(List<Persons> houseMembers) {
        this.houseMembers = houseMembers;
    }
}
