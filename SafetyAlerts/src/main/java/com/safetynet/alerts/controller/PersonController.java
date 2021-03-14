package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.exceptions.PersonAddException;
import com.safetynet.alerts.exceptions.PersonBadParameter;
import com.safetynet.alerts.exceptions.PersonNotFound;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.url.ChildInfo;
import com.safetynet.alerts.model.url.CommunityEmail;
import com.safetynet.alerts.model.url.InfoPersonFull;
import com.safetynet.alerts.model.url.PhoneInfo;
import com.safetynet.alerts.services.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class PersonController {
    private static final Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getPerson(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
        logger.info("Get person");
        List<Person> persons = personService.findPerson(firstName, lastName);

        if (persons.size() == 0) {
            logger.error("getPerson : Not Found");

        }
        return persons;
    }

    @GetMapping(value = "/personInfo")
    public MappingJacksonValue getPersonInfo(@RequestParam("firstName") String firstName,
                                             @RequestParam("lastName") String lastName) throws Exception {

        if (lastName.isEmpty()) {
            logger.error("getPersonInfo : lastName is empty");
            throw new Exception("lastName must be not empty");
        }
        logger.info("getPersonInfo success");

        List<InfoPersonFull> dtoObject = personService.getPersonInfo(firstName, lastName);

        MappingJacksonValue resultat = new MappingJacksonValue(dtoObject);
        FilterProvider filter = new SimpleFilterProvider().addFilter("filtreInformationPersonFull",
                SimpleBeanPropertyFilter.serializeAllExcept("phone", "station"));
        resultat.setFilters(filter);
        return resultat;
    }

    @GetMapping(value = "/childAlert")
    public List<ChildInfo> getChildAlert(@RequestParam("address") String address) throws Exception {
        logger.info("childAlert : success");

        return personService.getListChild(address);
    }

    @GetMapping(value = "/phoneAlert")
    public List<PhoneInfo> getphoneAlertByFireStation(@RequestParam("firestation") String station) throws Exception {
        logger.info("phoneAlert : success");

        return personService.getListPhoneInfo(station);

    }

    @GetMapping(value = "/communityEmail")
    public List<CommunityEmail> getCommunityEmail(@RequestParam("city") String city) throws Exception {

        if (city.isEmpty()) {
            logger.error("getCommunityEmail : city is empty");
            throw new Exception("city value is empty");
        }
        logger.info("get email success");
        return personService.getCommunityEmail(city);

    }

    @PostMapping(value = "person/post")
    public Person addPerson(@Valid @RequestBody Person person){
        Person personAdd = personService.save(person);
        if(personAdd == null){
            logger.error("addPerson : not ok");
            throw new PersonAddException("Add " + person.toString() + ": ERROR");
        }
        logger.info("Add "+personAdd.toString());
        return personAdd;
    }

    @PutMapping(value = "person/put")
    public Person modifyPerson(@RequestBody Person person){
        Person personModif = personService.update(person);
        if(personModif==null){
            logger.error("modifyPerson:Not Found");
            throw new PersonNotFound(person.toString());
        }
        logger.info("modifyPerson : success");
        return personModif;
    }

    @DeleteMapping(value = "person/delete")
    public String removePerson(@RequestParam("firstname")String firstName,@RequestParam("lastname")String lastName){
        if(firstName.isEmpty() || lastName.isEmpty())
            throw new PersonBadParameter("Firstname and lastname are required");

        if (personService.delete(firstName, lastName)){
            logger.info("Remove person succeeded");
            return "Deleted person : " + firstName + " " +lastName;
        } else
            throw new PersonNotFound(firstName+" "+lastName);
    }



}
