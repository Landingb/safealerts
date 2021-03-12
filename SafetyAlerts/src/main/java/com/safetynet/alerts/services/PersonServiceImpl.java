package com.safetynet.alerts.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.url.ChildInfo;
import com.safetynet.alerts.model.url.CommunityEmail;
import com.safetynet.alerts.model.url.InfoPersonFull;
import com.safetynet.alerts.model.url.PhoneInfo;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private FireStationService fireStationService;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public List<Person> findAll() {
        return personRepository.getAllPersons();
    }

    @Override
    public List<Person> findPerson(String firstName, String lastName) {
        return personRepository.findPersonByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Person> findPersonByAddress(String address) {
        return personRepository.findPersonByAdress(address);
    }

    @Override
    public List<ChildInfo> getListChild(String address) throws Exception {
        List<Person> allPersonInAdress = this.findPersonByAddress(address);

        List<ChildInfo> listChildInfo = new ArrayList<>();
        for (Person p : allPersonInAdress) {
            MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(p.getFirstName(), p.getLastName());
            if (medicalRecord != null) {
                int age = serviceUtil.calculateAge(medicalRecord.getBirthdate());

                if (serviceUtil.isChild(age)) {
                    ChildInfo childInfo = new ChildInfo();
                    childInfo.setFirstName(p.getFirstName());
                    childInfo.setLastName(p.getLastName());
                    childInfo.setAge(age);
                    childInfo.setHouseMembers(allPersonInAdress);
                    listChildInfo.add(childInfo);
                }
            }
        }
        return listChildInfo;
    }

    @Override
    public List<PhoneInfo> getListPhoneInfo(String station) {
        List<PhoneInfo> listPhoneInfo = new ArrayList<>();
        List<Person> listPersonFireStation = new ArrayList<>();
        List<String> listAddress = fireStationService.addressCoveredByStation(station);

        for (String a : listAddress) {
            List<Person> listPersonAddress = findPersonByAddress(a);
            listPersonFireStation.addAll(listPersonAddress);
        }

        PhoneInfo phoneInfo = new PhoneInfo();
        for (Person p : listPersonFireStation) {
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
        List<Person> persons = findPerson(firstName, lastName);

        for (Person p : persons) {
            InfoPersonFull infoPerson = getFullInformationPerson(p);
            result.add(infoPerson);
        }
        return result;
    }

    @Override
    public List<CommunityEmail> getCommunityEmail(String city) {
        List<CommunityEmail> result = new ArrayList<>();
        List<Person> persons = findAll();

        for (Person p : persons) {
            if (p.getCity().contentEquals(city)) {
                CommunityEmail communityEmail = new CommunityEmail();
                communityEmail.setEmail(p.getEmail());
                result.add(communityEmail);
            }
        }

        return result;
    }

    @Override
    public InfoPersonFull getFullInformationPerson(Person person) {

        InfoPersonFull result = new InfoPersonFull();
        result.setFirstName(person.getFirstName());
        result.setLastName(person.getLastName());
        result.setAddress(person.getAddress());
        result.setPhone(person.getPhone());
        result.setEmail(person.getEmail());
        result.setStation(fireStationService.stationNumber(person.getAddress()));

        MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(person.getFirstName(),
                person.getLastName());
        if (medicalRecord != null) {
            result.setAge(serviceUtil.calculateAge(medicalRecord.getBirthdate()));
            result.setAllergies(medicalRecord.getAllergies());
            result.setMedications(medicalRecord.getMedications());
        }

        return result;
    }

    @Override
    public Person save(Person person) {
        return personRepository.addPerson(person);
    }

    @Override
    public Person update(Person person) {
        return personRepository.updatePerson(person);
    }

    @Override
    public boolean delete(String firstName, String lastName) {
        return personRepository.deletePerson(firstName, lastName);
    }

}
