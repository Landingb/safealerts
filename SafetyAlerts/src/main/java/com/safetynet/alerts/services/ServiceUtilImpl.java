package com.safetynet.alerts.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

@Service
public class ServiceUtilImpl implements ServiceUtil {
    @Autowired
    MedicalRecordService medicalRecordService;
    
    @Override
    public int calculateAge(Date date) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date currentDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(date));
        int d2 = Integer.parseInt(formatter.format(currentDate));
        return (d2 - d1) / 10000;
    }


    @Override
    public Boolean isChild(Person person) throws ParseException {
        int agePerson = 0;
        MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(person.getFirstName(), person.getLastName());

        if (medicalRecord != null) {
            agePerson = calculateAge(medicalRecord.getBirthdate());
        }

        if (agePerson <= 18) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean isChild(int age) {

        if (age <= 18) {
            return true;
        } else {
            return false;
        }
    }
}
