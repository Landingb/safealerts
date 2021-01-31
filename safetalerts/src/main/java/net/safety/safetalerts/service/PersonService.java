package net.safety.safetalerts.service;

import net.safety.safetalerts.model.CommunityEmail;
import net.safety.safetalerts.model.Persons;
import net.safety.safetalerts.repository.PersonRepository;
import net.safety.safetalerts.url.ChildInfo;
import net.safety.safetalerts.url.InfoPersonFull;
import net.safety.safetalerts.url.PhoneInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void savePerson(Persons persons) {
        personRepository.save(persons);
    }

    public void deletePerson(String firstName, String lastName) {
        personRepository.deleteByNames(firstName, lastName);
    }

    public void getPersons(String firstName, String lastName) {
        personRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Persons> getAllPersons(){
        return personRepository.getPersons();
    }

    public List<CommunityEmail> getEmail(String city){
        return personRepository.getCommunityEmail(city);
    }

    public InfoPersonFull getFullInformationPerson(Persons person) {
        return personRepository.getFullInformationPerson(person);
    }

    public List<Persons> findPersonByAddress(String address) {
        return personRepository.findPersonByAddress(address);
    }

    public List<ChildInfo> getListChild(String address) throws Exception {
        return personRepository.getListChild(address);
    }

    public List<PhoneInfo> getListPhoneInfo(String station) throws Exception {
        return personRepository.getListPhoneInfo(station);
    }

    public List<InfoPersonFull> getPersonInfo(String firstName, String lastName){
        return personRepository.getPersonInfo(firstName, lastName);
    }
}
