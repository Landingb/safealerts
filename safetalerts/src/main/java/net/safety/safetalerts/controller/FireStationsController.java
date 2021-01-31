package net.safety.safetalerts.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import net.safety.safetalerts.model.FireStations;
import net.safety.safetalerts.model.Persons;
import net.safety.safetalerts.service.FireStationsService;
import net.safety.safetalerts.url.FloodHome;
import net.safety.safetalerts.url.InfoPersonFull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;




@RestController
public class FireStationsController {

    @Autowired
    private FireStationsService fireStationsService;

    @GetMapping("/firestations")
    public List<FireStations> getAllStations(){
        return this.fireStationsService.getAllStations();
    }

    @GetMapping(value = "/fire")
    public MappingJacksonValue getFireListPerson(@RequestParam("address") String address) throws Exception {

        if (address.isEmpty()) {

            throw new Exception("address value is empty");
        }

        List<InfoPersonFull> dtoObject=fireStationsService.getFireListPerson(address);

        MappingJacksonValue resultat = new MappingJacksonValue(dtoObject);
        FilterProvider filter = new SimpleFilterProvider().addFilter("filtreInformationPersonFull",
                SimpleBeanPropertyFilter.serializeAllExcept("email","station","address"));
        resultat.setFilters(filter);
        return resultat;

    }

    @GetMapping(value = "/flood")
    public MappingJacksonValue getFloodListHome(@RequestParam("stations") List<String> stations) throws Exception {

        if (stations.isEmpty()) {
            throw new Exception("list of stations is empty");
        }

        List<FloodHome> dtoObject = fireStationsService.getFloodListHome(stations);

        MappingJacksonValue resultat = new MappingJacksonValue(dtoObject);
        FilterProvider filter = new SimpleFilterProvider().addFilter("filtreInformationPersonFull",
                SimpleBeanPropertyFilter.serializeAllExcept("email", "age", "station"));
        resultat.setFilters(filter);
        return resultat;

    }


    @PostMapping("/firestation")
    public void  createStation(@RequestBody FireStations stations) {
        if (stations == null){
            FireStations currentStation = new FireStations();
            FireStations fireStations = currentStation;

            String address = fireStations.getAddress();
            currentStation.setAddress(address);

            Integer station = fireStations.getStation();
            currentStation.setStation(station);

            fireStationsService.saveStation(currentStation);

        }

    }

    @PutMapping("/firestation/{update}")
    public FireStations updateStations(@PathVariable("address") final String address, @RequestBody FireStations stations) {
        fireStationsService.getStation(stations);
        if(stations!=null) {

            FireStations currentStation = new FireStations();
            FireStations fireStations = currentStation;

            Integer fStation = fireStations.getStation();
            if(fStation != null) {
                currentStation.setStation(fStation);
            }

            fireStationsService.saveStation(currentStation);
            return currentStation;

        } else {
            return null;
        }
    }

    @DeleteMapping("/firestation/{byStation}")
    public void deleteStation(Optional <String> address, Optional <Integer> station) {
        fireStationsService.deleteStation(address, station);
    }
}
