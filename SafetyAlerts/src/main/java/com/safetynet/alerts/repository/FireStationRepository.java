package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.FireStation;

public interface FireStationRepository {
    public List<FireStation> getAllFireStations();
    public List<String> getadressFireStationByStation(String station);
    public String getStationByAddress(String address);
    public void setAllFireStations(List<FireStation> listFireStations);  
    public FireStation addFirestation(FireStation fireStation);
    public FireStation updateFirestation(FireStation fireStation);

    public List<FireStation> deleteFirestation(FireStation fireStation);

}
