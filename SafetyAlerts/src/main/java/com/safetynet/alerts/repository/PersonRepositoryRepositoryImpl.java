package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public class PersonRepositoryRepositoryImpl implements PersonRepository {

    private List<Person> persons = new ArrayList<>();

    @Override
    public List<Person> getAllPersons() {

        return persons;
    }


    @Override
    public List<Person> findPersonByFirstNameAndLastName(String firstName, String lastName) {
        List<Person> result = new ArrayList<>();

        if (!firstName.isEmpty()) {
            for (Person p : persons) {
                if ((p.getFirstName().contentEquals(firstName)) && (p.getLastName().contentEquals(lastName)))
                    result.add(p);
            }
        } else {
            for (Person p : persons) {
                if (p.getLastName().contentEquals(lastName))
                    result.add(p);
            }
        }
        return result;
    }

    @Override
    public void setAllPersons(List<Person> listPerson) {
        this.persons = listPerson;

    }

    @Override
    public Person addPerson(Person person) {
        persons.add(person);
        return person;
    }

    @Override
    public Person updatePerson(Person person) {
        int index =0;
        for(Person p:persons){
            if(p.getFirstName().contentEquals(person.getFirstName())&&(p.getLastName().contentEquals(person.getLastName()))){
                persons.set(index, person);
                return person;
            }
            index++;
        }
        return null;
    }

    @Override
    public boolean deletePerson(String firstName, String lastName) {
        for (Person p:persons){
            if(p.getFirstName().contentEquals(firstName)&&(p.getLastName().contentEquals(lastName)));
            persons.remove(p);
            return true;
        }

        return false;
    }

    @Override
    public List<Person> findPersonByAdress(String address) {
        List<Person> personByAddress = new ArrayList<>();

        for (Person p : persons) {

            if (p.getAddress().contentEquals(address)) {

                personByAddress.add(p);
            }
        }

        return personByAddress;
    }

}
