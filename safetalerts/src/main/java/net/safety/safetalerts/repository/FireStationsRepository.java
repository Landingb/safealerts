package net.safety.safetalerts.repository;


import net.safety.safetalerts.model.FireStations;
import net.safety.safetalerts.url.FloodHome;
import net.safety.safetalerts.url.InfoPersonFull;

import java.util.List;
import java.util.Optional;

public interface FireStationsRepository {

    void findByStation(FireStations stations);

    void save(FireStations currentStation);

    void deleteByStation(Optional<String> address, Optional<Integer> station);

    List<FireStations> getStations();

    List<InfoPersonFull> getFireListPerson(String address);

    String stationNumber(String address);

    List<String> getAddressFireStationByStation(String station);

    String getStationByAddress(String address);

    List<FloodHome> getFloodListHome(List<String> stations);

    List<String> addressCoveredByStations(String station);
}
