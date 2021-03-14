package com.safetynet.alerts.controller;

import com.safetynet.alerts.exceptions.MedicalRecordNotFound;
import com.safetynet.alerts.exceptions.MedicalRecordPersonNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.services.MedicalRecordService;


@RestController
@RequestMapping
public class MedicalRecordController {
    private static final Logger logger = LogManager.getLogger("MedicalRecordController");

    @Autowired
    private MedicalRecordService medicalRecordService;


    @GetMapping("/medicalrecord")
    public MedicalRecord getMedicalRecord(@RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName) {

        if (medicalRecordService.findMedicalRecord(firstName, lastName) != null) {
            logger.info("Get record");
            return medicalRecordService.findMedicalRecord(firstName, lastName);
        } else {
            logger.error("error");

        }
        return medicalRecordService.findMedicalRecord(firstName, lastName);
    }

    @PostMapping(value ="medicalrecord/post")
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        MedicalRecord recordAdd = medicalRecordService.save(medicalRecord);
        if(medicalRecord==null){
            logger.error("addMedicalRecord not ok");
            throw new MedicalRecordPersonNotFound(medicalRecord.getFirstName()+" "+medicalRecord.getLastName());
        }
        logger.info("created medicalrecord:"+medicalRecord.getFirstName()+" "+medicalRecord.getLastName());
        return recordAdd;
    }

    @PutMapping(value ="medicalrecord/put")
    public MedicalRecord modifyMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        MedicalRecord recordModified = medicalRecordService.update(medicalRecord);
        if(recordModified==null){
            logger.error("modifyMedicalRecored not ok");
            throw new MedicalRecordNotFound(medicalRecord.getFirstName()+" "+medicalRecord.getLastName());
        }
        logger.info("modified medicalrecord: "+ medicalRecord.getFirstName()+" "+medicalRecord.getLastName());
        return recordModified;
    }

    @DeleteMapping(value ="medicalrecord/delete")
    public String removeMedicalRecord(@RequestParam("firstname")String firstName, @RequestParam("lastname")String lastName){
        String medicalRecordName = firstName+" "+lastName;
        if(medicalRecordService.delete(firstName, lastName)){
            logger.info("Deleteted medicalrecord :"+medicalRecordName);
            return "Deleted medicalrecord :"+medicalRecordName;
        } else{
            logger.error("removeMedicalRecord not ok");
            throw new MedicalRecordNotFound(medicalRecordName);
        }
    }


}
