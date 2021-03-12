package com.safetynet.alerts.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.url.FireStationCoverage;
import com.safetynet.alerts.model.url.InfoPersonFull;
import com.safetynet.alerts.model.url.FloodHome;;

@Service
public class FireStationServiceImpl implements FireStationService {

    @Autowired
    private FireStationRepository firestationRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Override
    public List<FireStation> findAll() {
        return firestationRepository.getAllFireStations();
    }

    @Override
    public String stationNumber(String address) {
        return firestationRepository.getStationByAddress(address);
    }


    @Override
    public List<String> addressCoveredByStation(String station) {
        return firestationRepository.getadressFireStationByStation(station);
    }

    @Override
    public FireStationCoverage fireStationPersonsCovered(String station) throws ParseException {

        FireStationCoverage fireStationCoverage = new FireStationCoverage();
        List<InfoPersonFull> fireStationPersons = new ArrayList<>();

        List<Person> personByStation = new ArrayList<>();

        List<String> adressCoveredByStation = addressCoveredByStation(station);

        int countChild = 0;
        int countAdult = 0;

        // list of person
        for (String a : adressCoveredByStation) {
            List<Person> personByAddress = personService.findPersonByAddress(a);
            personByStation.addAll(personByAddress);
        }

        for (Person p : personByStation) {
            InfoPersonFull infoPersonFull = personService.getFullInformationPerson(p);

            if (serviceUtil.isChild(p)) {
                countChild++;
            } else
                countAdult++;

            fireStationPersons.add(infoPersonFull);
        }

        fireStationCoverage.setFireStationPersons(fireStationPersons);
        fireStationCoverage.setAdultCount(countAdult);
        fireStationCoverage.setChildCount(countChild);

        return fireStationCoverage;

    }

    @Override
    public List<InfoPersonFull> getFireListPerson(String address) {
        List<InfoPersonFull> fireStationListPerson = new ArrayList<>();

        List<Person> personByAddress = personService.findPersonByAddress(address);

        for (Person p : personByAddress) {
            InfoPersonFull infoPersonFull = personService.getFullInformationPerson(p);
            fireStationListPerson.add(infoPersonFull);
        }

        return fireStationListPerson;
    }

    @Override
    public List<FloodHome> getFloodListHome(List<String> stations) {

        List<FloodHome> result = new ArrayList<>();
        List<String> adresses = new ArrayList<>();

        for (String s : stations) {
            List<String> adressesForOneFireStation = new ArrayList<>();
            adressesForOneFireStation = addressCoveredByStation(s);
            adresses.addAll(adressesForOneFireStation);
        }

        for (String a : adresses) {
            List<Person> listPersons = new ArrayList<>();
            listPersons = personService.findPersonByAddress(a);

            FloodHome floodHome = new FloodHome();
            floodHome.setAddress(a);

            List<InfoPersonFull> listFloodPerson = new ArrayList<>();
            for (Person p : listPersons) {

                InfoPersonFull infoPersonFull = personService.getFullInformationPerson(p);

                listFloodPerson.add(infoPersonFull);
            }
            floodHome.setFloodListPersons(listFloodPerson);
            result.add(floodHome);
        }

        return result;
    }

    @Override
    public FireStation save(FireStation fireStation) {
        return firestationRepository.addFirestation(fireStation);
    }

    @Override
    public FireStation update(FireStation fireStation) {
        return firestationRepository.updateFirestation(fireStation);
    }

    @Override
    public List<FireStation> delete(FireStation fireStation) {
        return firestationRepository.deleteFirestation(fireStation);
    }

}
