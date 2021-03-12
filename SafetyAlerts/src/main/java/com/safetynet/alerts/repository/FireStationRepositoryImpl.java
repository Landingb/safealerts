package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import com.safetynet.alerts.model.Person;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.FireStation;

@Repository
public class FireStationRepositoryImpl implements FireStationRepository {
    private List<FireStation> fireStations = new ArrayList<>();

    @Override
    public List<FireStation> getAllFireStations() {

        return fireStations;
    }



    @Override
    public void setAllFireStations(List<FireStation> listFireStations) {
        this.fireStations = listFireStations;

    }

    @Override
    public FireStation addFirestation(FireStation fireStation) {
        fireStations.add(fireStation);
        return fireStation;
    }

    @Override
    public FireStation updateFirestation(FireStation fireStation) {
        int index = 0;

        for (FireStation f : fireStations) {
            if (f.getAddress().contentEquals(fireStation.getAddress())) {
                fireStations.set(index, fireStation);
                return fireStation;
            }
            index++;
        }
        return null;
    }


    @Override
    public List<FireStation> deleteFirestation(FireStation fireStation) {
        List<FireStation> fireStationsDeleted = new ArrayList<>();

        //with address and station
        if(!fireStation.getAddress().isEmpty() && !fireStation.getStation().isEmpty()){
            for (FireStation f: fireStations){
                if((f.getStation().contentEquals(fireStation.getStation())) && (f.getAddress().contentEquals(fireStation.getAddress()))){
                    fireStationsDeleted.add(f);
                }
            }
        }
        if(fireStationsDeleted.size()!=0)
            fireStations.removeAll(fireStationsDeleted);


        return fireStationsDeleted;
    }

    @Override
    public List<String> getadressFireStationByStation(String station) {
        List<String> listAddress = new ArrayList<>();

        for (FireStation f : fireStations) {
            if (f.getStation().contentEquals(station)) {
                listAddress.add(f.getAddress());
            }
        }

        return listAddress;
    }

    @Override
    public String getStationByAddress(String address) {
        for (FireStation f : fireStations) {
            if (f.getAddress().contentEquals(address)) {
                return f.getStation();
            }
        }
        return "Not Found";
    }

}
