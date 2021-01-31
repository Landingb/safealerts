package net.safety.safetalerts.url;

import java.util.List;

public class FloodHome {
    private String address;
    List<InfoPersonFull> FloodListPersons;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<InfoPersonFull> getFloodListPersons() {
        return FloodListPersons;
    }

    public void setFloodListPersons(List<InfoPersonFull> floodListPersons) {
        FloodListPersons = floodListPersons;
    }
}
