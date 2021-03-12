package com.safetynet.alerts.controller;

import java.util.List;

import com.safetynet.alerts.exceptions.FireStationNotFound;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.url.FloodHome;
import com.safetynet.alerts.model.url.InfoPersonFull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.model.url.FireStationCoverage;
import com.safetynet.alerts.services.FireStationService;


@RestController
public class FireStationController {
    private static final Logger logger = LogManager.getLogger("FireStationController");

    @Autowired
    private FireStationService fireStationService;


   @GetMapping(value = "/firestation")
   public MappingJacksonValue getFireStationCoverage(@RequestParam("stationNumber") String station) throws Exception {
       logger.info("getFireStationCoverage sucess");
       
       
       FireStationCoverage dtoObject=fireStationService.fireStationPersonsCovered(station);
       
       MappingJacksonValue resultat = new MappingJacksonValue(dtoObject);
       FilterProvider filter = new SimpleFilterProvider().addFilter("filtreInformationPersonFull",
               SimpleBeanPropertyFilter.serializeAllExcept("email","age","medications","allergies","station"));
       resultat.setFilters(filter);
       return resultat;

    }

    @GetMapping(value = "/flood/stations")
    public MappingJacksonValue getFloodListHome(@RequestParam("stations") List<String> stations) throws Exception {

        if (stations.isEmpty()) {
            logger.error("parameter is empty");
            throw new Exception("list of stations is empty");
        }
        logger.info("getFloodListHome sucess");

        List<FloodHome> dtoObject = fireStationService.getFloodListHome(stations);

        MappingJacksonValue resultat = new MappingJacksonValue(dtoObject);
        FilterProvider filter = new SimpleFilterProvider().addFilter("filtreInformationPersonFull",
                SimpleBeanPropertyFilter.serializeAllExcept("email", "age", "station"));
        resultat.setFilters(filter);
        return resultat;

    }

    @GetMapping(value = "/fire")
    public MappingJacksonValue getFireListPerson(@RequestParam("address") String address) throws Exception {

        if (address.isEmpty()) {
            logger.error("getFireListPerson : parameter is empty");
            throw new Exception("address value is empty");
        }
        logger.info("getFireListPerson success");

        List<InfoPersonFull> dtoObject=fireStationService.getFireListPerson(address);

        MappingJacksonValue resultat = new MappingJacksonValue(dtoObject);
        FilterProvider filter = new SimpleFilterProvider().addFilter("filtreInformationPersonFull",
                SimpleBeanPropertyFilter.serializeAllExcept("email","station","address"));
        resultat.setFilters(filter);
        return resultat;

    }

    @PostMapping(value = "/firestation/post")
    @ResponseStatus(HttpStatus.CREATED)
    public FireStation addFireStation(@RequestBody FireStation fireStation){
       logger.info("Add firestation:"+ fireStation.toString());
       return fireStationService.save(fireStation);
    }

    @PutMapping(value = "/firestation/put")
    public FireStation modifyFireStation(@RequestBody FireStation fireStation) {

        FireStation fireStationModified = fireStationService.update(fireStation);

        if (fireStationModified != null) {
            logger.info("Modified firestation : " + fireStationModified.toString());
            return fireStationModified;
        } else {
            logger.error("Modified firestation : KO");
            throw new FireStationNotFound(fireStation.toString());
        }
    }

    @DeleteMapping(value = "/firestation/delete")
    public List<FireStation> removeFireStation(@RequestBody FireStation fireStation){
       List<FireStation> stationsDeleted = fireStationService.delete(fireStation);
       if(stationsDeleted.size()!=0){
           logger.info("deleted firestation :" + stationsDeleted.toString());
           return stationsDeleted;
       } else {
           logger.error("removeFireStation: KO");
           throw new FireStationNotFound(fireStation.toString());       }
    }

}
