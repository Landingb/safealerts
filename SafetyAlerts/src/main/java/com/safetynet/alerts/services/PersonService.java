package com.safetynet.alerts.services;

import java.util.List;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.url.ChildInfo;
import com.safetynet.alerts.model.url.CommunityEmail;
import com.safetynet.alerts.model.url.InfoPersonFull;
import com.safetynet.alerts.model.url.PhoneInfo;


public interface PersonService {
    public List<Person> findAll();

    public List<Person> findPerson(String firstName, String lastName);

    public List<Person> findPersonByAddress(String address);

    public List<ChildInfo> getListChild(String address) throws Exception;

    public List<PhoneInfo> getListPhoneInfo(String station);

    public List<InfoPersonFull> getPersonInfo(String firstName, String lastName);

    public List<CommunityEmail> getCommunityEmail(String city);
    
    public InfoPersonFull getFullInformationPerson(Person person);

    public Person save(Person person);

    public Person update(Person person);

    public boolean delete(String firstName, String lastName);

}
