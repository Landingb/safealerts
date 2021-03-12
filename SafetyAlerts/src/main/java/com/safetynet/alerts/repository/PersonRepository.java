package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface PersonRepository {
      
    public List<Person> getAllPersons();
    public List<Person> findPersonByFirstNameAndLastName(String firstName, String lastname); 
    public List<Person> findPersonByAdress(String address);
    public void setAllPersons(List<Person> listPerson);
    public Person addPerson(Person person);
    public Person updatePerson(Person person);
    public boolean deletePerson(String firstName, String lastName);
    

}
