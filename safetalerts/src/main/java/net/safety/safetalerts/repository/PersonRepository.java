package net.safety.safetalerts.repository;

import net.safety.safetalerts.model.CommunityEmail;
import net.safety.safetalerts.model.Persons;
import net.safety.safetalerts.url.ChildInfo;
import net.safety.safetalerts.url.InfoPersonFull;
import net.safety.safetalerts.url.PhoneInfo;

import java.util.List;


public interface PersonRepository  {

    void deleteByNames(String firstName, String lastName);

    void findByFirstNameAndLastName(String firstName, String lastName);

    Persons save(Persons persons);

    List<Persons> getPersons();

    List<CommunityEmail> getCommunityEmail(String city);

    InfoPersonFull getFullInformationPerson(Persons person);

    List<ChildInfo> getListChild(String address) throws Exception;


    List<PhoneInfo> getListPhoneInfo(String station);

    List<InfoPersonFull> getPersonInfo(String firstName, String lastName);

    List<Persons> findPersonByAddress(String address);

}
