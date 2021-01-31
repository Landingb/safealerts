package net.safety.safetalerts.repository;

import net.safety.safetalerts.Database;
import net.safety.safetalerts.model.CommunityEmail;
import net.safety.safetalerts.model.FireStations;
import net.safety.safetalerts.model.MedicalRecords;
import net.safety.safetalerts.model.Persons;

import net.safety.safetalerts.service.FireStationsService;
import net.safety.safetalerts.service.MedicalRecordsService;

import net.safety.safetalerts.service.PersonService;
import net.safety.safetalerts.service.ServiceUtil;
import net.safety.safetalerts.url.ChildInfo;
import net.safety.safetalerts.url.InfoPersonFull;
import net.safety.safetalerts.url.PhoneInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    @Autowired
    private Database database;

    @Autowired
    private MedicalRecordsService medicalRecordsService;

    @Autowired
    private FireStationsService fireStationsService;

    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private PersonService personService;


    @Override
    public void deleteByNames(String firstName, String lastName) {

    }

    @Override
    public void findByFirstNameAndLastName(String firstName, String lastName) {
        database.getPersonsL();
    }

    @Override
    public Persons save(Persons persons) {
       database.getPersonsL().add(persons);
        return persons;
    }

    @Override
    public List<Persons> getPersons(){
        return database.getPersonsL();
    }

    @Override
    public List<CommunityEmail> getCommunityEmail(String city) {
        List<CommunityEmail> result = new ArrayList<>();
        List<Persons> persons = getPersons();

        for (Persons p : persons) {
            if (p.getCity().contentEquals(city)) {
                CommunityEmail communityEmail = new CommunityEmail();
                communityEmail.setEmail(p.getEmail());
                result.add(communityEmail);
            }
        }
        return result;
    }

    @Override
    public InfoPersonFull getFullInformationPerson(Persons person) {
        InfoPersonFull result = new InfoPersonFull();
        result.setFirstName(person.getFirstName());
        result.setLastName(person.getLastName());
        result.setAddress(person.getAddress());
        result.setPhone(person.getPhone());
        result.setEmail(person.getEmail());
        result.setStation(fireStationsService.stationNumber(person.getAddress()));

        MedicalRecords medicalRecord = medicalRecordsService.getRecords(person.getFirstName(), person.getLastName());
        if (medicalRecord != null) {
            result.setAge(serviceUtil.calculateAge(medicalRecord.getBirthdate()));
            result.setAllergies(medicalRecord.getAllergies());
            result.setMedications(medicalRecord.getMedications());
        }

        return result;
    }

    @Override
    public List<ChildInfo> getListChild(String address) throws Exception {
        List<Persons> allPersonInAddress = this.findPersonByAddress(address);

        List<ChildInfo> listChildInfo = new ArrayList<>();
        for (Persons p : allPersonInAddress) {
            MedicalRecords medicalRecord = medicalRecordsService.getRecords(p.getFirstName(), p.getLastName());
            if (medicalRecord != null) {
                int age = serviceUtil.calculateAge(medicalRecord.getBirthdate());

                if (serviceUtil.isChild(age)) {
                    ChildInfo childInfo = new ChildInfo();
                    childInfo.setFirstName(p.getFirstName());
                    childInfo.setLastName(p.getLastName());
                    childInfo.setAge(age);
                    childInfo.setHouseMembers(allPersonInAddress);
                    listChildInfo.add(childInfo);
                }
            }
        }
        return listChildInfo;
    }

    @Override
    public List<PhoneInfo> getListPhoneInfo(String station) {
        List<PhoneInfo> listPhoneInfo = new ArrayList<>();
        List<Persons> listPersonFireStation = new ArrayList<>();
        List<String> listAddress = fireStationsService.addressCoveredByStation(station);

        for (String a : listAddress) {
            List<Persons> listPersonAddress = findPersonByAddress(a);
            listPersonFireStation.addAll(listPersonAddress);
        }

        PhoneInfo phoneInfo = new PhoneInfo();
        for (Persons p : listPersonFireStation) {
            phoneInfo.setFirstName(p.getFirstName());
            phoneInfo.setLastName(p.getLastName());
            phoneInfo.setPhone(p.getPhone());
            listPhoneInfo.add(phoneInfo);
        }

        return listPhoneInfo;
    }

    @Override
    public List<InfoPersonFull> getPersonInfo(String firstName, String lastName) {
        List<InfoPersonFull> result = new ArrayList<>();
        List<Persons> persons = getPersons();

        for (Persons p : persons) {
            InfoPersonFull infoPerson = getFullInformationPerson(p);
            result.add(infoPerson);
        }
        return result;
    }

    @Override
    public List<Persons> findPersonByAddress(String address) {
        List<Persons> personByAddress = new ArrayList<>();

        for (Persons p : personByAddress) {

            if (p.getAddress().contentEquals(address)) {

                personByAddress.add(p);
            }
        }
        return personByAddress;
    }
}
/*
Maintenant que nous avons mis en place la database, utlise là afin de rechercher
dans tes données à l'aide des getters pour récupérer tes List<Persons>, List<MedicalRecords> et List<FireStations>.

Utiliser les boucles for que nous avons vu ensemble afin de parcourir les éléments;

Utiliser les if afin de trouver les éléments et les renvoyer aux services.
*/