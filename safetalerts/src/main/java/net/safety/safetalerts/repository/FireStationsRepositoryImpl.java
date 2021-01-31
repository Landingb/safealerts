package net.safety.safetalerts.repository;

import net.safety.safetalerts.Database;
import net.safety.safetalerts.model.FireStations;

import net.safety.safetalerts.model.Persons;
import net.safety.safetalerts.service.FireStationsService;
import net.safety.safetalerts.service.PersonService;
import net.safety.safetalerts.url.FloodHome;
import net.safety.safetalerts.url.InfoPersonFull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FireStationsRepositoryImpl implements FireStationsRepository{

    private List<FireStations> fireStations = new ArrayList<>();

    @Autowired
    private Database database;
    @Autowired
    private PersonService personService;
    @Autowired
    private FireStationsService fireStationsService;


    @Override
    public void findByStation(FireStations stations) {

    }

    @Override
    public void save(FireStations currentStation) {

    }

    @Override
    public void deleteByStation(Optional<String> address, Optional<Integer> station) {

    }

    @Override
    public List<FireStations> getStations() {
        return database.getStations();
    }


    @Override
    public List<InfoPersonFull> getFireListPerson(String address) {
        List<InfoPersonFull> fireStationListPerson = new ArrayList<>();

        List<Persons> personByAddress = personService.findPersonByAddress(address);

        for (Persons p : personByAddress) {
            InfoPersonFull infoPersonFull = personService.getFullInformationPerson(p);
            fireStationListPerson.add(infoPersonFull);
        }

        return fireStationListPerson;
    }

    @Override
    public String stationNumber(String address) {
        return fireStationsService.stationNumber(address);
    }

    @Override
    public List<String> getAddressFireStationByStation(String station) {
        List<String> listAddress = new ArrayList<>();

        for (FireStations f : fireStations) {
            if (f.getStation()== Integer.parseInt((station))) {
                listAddress.add(f.getAddress());
            }
        }

        return listAddress;
    }

    @Override
    public String getStationByAddress(String address) {
        List<FireStations> fireStations = fireStationsService.getAllStations();
        for (FireStations f : fireStations) {
            if (f.getAddress().contentEquals(address)) {
                return f.getAddress();
            }
        }
        return "Not Found";
    }


    @Override
    public List<FloodHome> getFloodListHome(List<String> stations) {

        List<FloodHome> result = new ArrayList<>();
        List<String> addresses = new ArrayList<>();

        for (String station : stations) {
            List<String> addressesForOneFireStation;
            addressesForOneFireStation = addressCoveredByStations(station);
            addresses.addAll(addressesForOneFireStation);
        }

        for (String a : addresses) {
            List<Persons> listPersons = new ArrayList<>();
            listPersons = personService.findPersonByAddress(a);

            FloodHome floodHome = new FloodHome();
            floodHome.setAddress(a);

            List<InfoPersonFull> listFloodPerson = new ArrayList<>();
            for (Persons p : listPersons) {

                InfoPersonFull infoPersonFull = personService.getFullInformationPerson(p);

                listFloodPerson.add(infoPersonFull);
            }
            floodHome.setFloodListPersons(listFloodPerson);
            result.add(floodHome);
        }

        return result;
    }

    @Override
    public List<String> addressCoveredByStations(String station) {
            return fireStationsService.addressCoveredByStation(station);

    }

}



