package com.safetynet.alerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.services.FireStationService;
import com.safetynet.alerts.services.MedicalRecordService;
import com.safetynet.alerts.services.PersonService;

@RestController
public class ListController {
    private static final Logger logger = LogManager.getLogger("ListController");

    @Autowired
    private FireStationService fireStationService;
    @Autowired
    private MedicalRecordService medicalRecordService;
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/persons")
    public List<Person> listePersons() {
        logger.info("Get list person");
        return personService.findAll();
    }

    @GetMapping(value = "/firestations")
    public List<FireStation> listeFireStaion() {
        logger.info("Get list fireStation");
        return fireStationService.findAll();
    }

    @GetMapping(value = "/medicalrecords")
    public List<MedicalRecord> listeMedicalRecord() {
        logger.info("Get list medical record");
        return medicalRecordService.findAll();
    }

}
