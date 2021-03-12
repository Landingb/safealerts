package com.safetynet.alerts.model;

import java.util.List;

public class FireStation {
    private String address;
    private String station;
    private List<Person> persons;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public FireStation() {
        super();
    }

    public FireStation(String address, String station) {
        super();
        this.address = address;
        this.station = station;
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getStation() {
        return station;
    }
    public void setStation(String station) {
        this.station = station;
    }
    @Override
    public String toString() {
        return "FireStation [address=" + address + ", station=" + station + "]";
    }
    

}
