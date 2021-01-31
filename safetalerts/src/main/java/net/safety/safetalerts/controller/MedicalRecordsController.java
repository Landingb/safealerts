package net.safety.safetalerts.controller;

import net.safety.safetalerts.model.MedicalRecords;
import net.safety.safetalerts.service.MedicalRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordsController {

    @Autowired
    private MedicalRecordsService medicalRecordsService;

    @GetMapping("/records")
    public List<MedicalRecords> getAllRecords(){
        return this.medicalRecordsService.getAllRecords();
    }

}
