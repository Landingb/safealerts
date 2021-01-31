package net.safety.safetalerts.service;


import net.safety.safetalerts.model.FireStations;
import net.safety.safetalerts.repository.FireStationsRepository;
import net.safety.safetalerts.url.FloodHome;
import net.safety.safetalerts.url.InfoPersonFull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FireStationsService {

    @Autowired
    private FireStationsRepository fireStationsRepository;


    public void saveStation(FireStations currentStation) {
        fireStationsRepository.save(currentStation);
    }


    public void getStation(FireStations stations) {
        fireStationsRepository.findByStation(stations);
    }

    public void deleteStation(Optional<String> address, Optional<Integer> station) {
        fireStationsRepository.deleteByStation(address, station);
    }

    public List<FireStations> getAllStations(){
        return fireStationsRepository.getStations();
    }

    public List<InfoPersonFull> getFireListPerson(String address) {
        return fireStationsRepository.getFireListPerson(address);
    }

   public String stationNumber(String address) {
        return fireStationsRepository.getStationByAddress(address);
    }

    public List<String> addressCoveredByStation(String station) {
        return fireStationsRepository.getAddressFireStationByStation(station);
    }


    public List<FloodHome> getFloodListHome(List<String> stations) {
        return fireStationsRepository.getFloodListHome(stations);
    }
}
