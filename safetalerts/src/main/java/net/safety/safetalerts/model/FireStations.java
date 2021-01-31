package net.safety.safetalerts.model;

import java.util.ArrayList;
import java.util.List;

public class FireStations {
    private String address;
    private int station;

    private List<Persons> persons =new ArrayList<Persons>();

    public List<Persons> getPersons() {
        return persons;
    }

    public void setPersons(List<Persons> persons) {
        this.persons = persons;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "FireStations{" +
                "address='" + address + '\'' +
                ", station=" + station +
                ", persons=" + persons +
                '}';
    }
}
