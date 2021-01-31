package net.safety.safetalerts.controller;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import net.safety.safetalerts.model.CommunityEmail;
import net.safety.safetalerts.model.Persons;
import net.safety.safetalerts.service.PersonService;
import net.safety.safetalerts.url.ChildInfo;
import net.safety.safetalerts.url.InfoPersonFull;
import net.safety.safetalerts.url.PhoneInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;



import java.util.List;


@RestController
public class PersonsController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public List<Persons> getAllPersons(){
        return this.personService.getAllPersons();
    }

    @GetMapping(value = "/childAlert")
    public List<ChildInfo> getChildAlert(@RequestParam("address") String address) throws Exception {
        return personService.getListChild(address);
    }

    @GetMapping(value = "/phoneAlert")
    public List<PhoneInfo> getphoneAlertByFireStation(@RequestParam("firestation") String station) throws Exception {
        return personService.getListPhoneInfo(station);
    }

    @GetMapping(value = "/personInfo")
    public MappingJacksonValue getPersonInfo(@RequestParam("firstName") String firstName,
                                             @RequestParam("lastName") String lastName) throws Exception {

        if (lastName.isEmpty()) {
            throw new Exception("lastName must be not empty");
        }

        List<InfoPersonFull> dtoObject = personService.getPersonInfo(firstName, lastName);

        MappingJacksonValue resultat = new MappingJacksonValue(dtoObject);
        FilterProvider filter = new SimpleFilterProvider().addFilter("filtreInformationPersonFull",
                SimpleBeanPropertyFilter.serializeAllExcept("phone", "station"));
        resultat.setFilters(filter);
        return resultat;

    }

    @GetMapping(value = "/communityEmail")
    public List<CommunityEmail> getCommunityEmail(@RequestParam("city") String city) throws Exception {

        if (city.isEmpty()) {
            //logger.error("getCommunityEmail : city is empty");
            throw new Exception("city value is empty");
        }
       // logger.info("getCommunityEmail sucess");
        return personService.getEmail(city);
    }

    @PostMapping("/person")
    public void  createPerson(@RequestBody Persons person) {
        if (person == null){
            Persons currentPerson = new Persons();

            String firstName = person.getFirstName();
            currentPerson.setFirstName(firstName);
            String lastName = person.getLastName();
            currentPerson.setFirstName(lastName);
            String address = person.getAddress();
            currentPerson.setAddress(address);
            String city = person.getCity();
            currentPerson.setCity(city);
            Integer zip =person.getZip();
            currentPerson.setZip(zip);
            String phone = person.getPhone();
            currentPerson.setPhone(phone);
            String mail = person.getEmail();
            currentPerson.setEmail(mail);

            personService.savePerson(currentPerson);

        }
    }

    @PutMapping("/person/{update}")
    public Persons updatePersons(@PathVariable("firstname") final String firstName, @PathVariable("lastName") String lastName, @RequestBody Persons person) {
        personService.getPersons(firstName, lastName);
        if(person!=null) {

            Persons currentPerson = new Persons();
            Persons persons = currentPerson;

            String address = person.getAddress();
            if(address != null) {
                currentPerson.setAddress(address);
            }
            String city = person.getCity();
            if(city != null) {
                currentPerson.setCity(city);
            }
            Integer zip = person.getZip();
            if(zip !=null){
                currentPerson.setZip(zip);
            }
            String phone = person.getPhone();
            if (phone !=null){
                currentPerson.setPhone(phone);
            }
            String mail = person.getEmail();
            if (mail !=null){
                currentPerson.setEmail(mail);
            }
            personService.savePerson(currentPerson);
            return currentPerson;
        } else {
            return null;
        }
    }

    @DeleteMapping("/person/{byNames}")
    public void deletePerson(@PathVariable("firstName") String FirstName, @PathVariable("lastName") String LastName) {
        personService.deletePerson(FirstName, LastName);
    }
}
