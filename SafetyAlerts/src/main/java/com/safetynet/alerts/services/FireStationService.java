package com.safetynet.alerts.services;

import java.text.ParseException;
import java.util.List;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.url.FireStationCoverage;
import com.safetynet.alerts.model.url.FloodHome;
import com.safetynet.alerts.model.url.InfoPersonFull;

public interface FireStationService {
    
    public List<FireStation> findAll();

    public String stationNumber(String adress);
    
    public List<String> addressCoveredByStation(String station); 

    public FireStationCoverage fireStationPersonsCovered(String station) throws ParseException;

    public List<InfoPersonFull> getFireListPerson(String address);

    public List<FloodHome> getFloodListHome(List<String> stations);

    FireStation save(FireStation fireStation);

    FireStation update(FireStation fireStation);

    List<FireStation> delete(FireStation fireStation);
}
