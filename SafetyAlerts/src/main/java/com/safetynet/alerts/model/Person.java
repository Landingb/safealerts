package com.safetynet.alerts.model;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

import java.util.List;

public class Person {



    @Length(min = 1)
    private String firstName;
    @Length(min = 1)
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    @Email
    private String email;
    private List<FireStation> station;

    public List<FireStation> getStation() {
        return station;
    }

    public void setStation(List<FireStation> station) {
        this.station = station;
    }

    public List<MedicalRecord> getRecord() {
        return record;
    }

    public void setRecord(List<MedicalRecord> record) {
        this.record = record;
    }

    private List<MedicalRecord> record;


    public Person() {
        super();
    }

    public Person(String firstName, String lastName, String address, String city, String zip, String phone,
            String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person [firstName=" + firstName + ", lastName=" + lastName + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }

}
