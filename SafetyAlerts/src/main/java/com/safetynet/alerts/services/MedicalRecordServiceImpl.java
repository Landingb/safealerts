package com.safetynet.alerts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.repository.MedicalRecordsRepository;
import com.safetynet.alerts.model.MedicalRecord;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    @Autowired
    private MedicalRecordsRepository MedicalRecordsRepository;

    @Autowired
    private PersonService personService;

    @Override
    public List<MedicalRecord> findAll() {
        return MedicalRecordsRepository.getAllMedicalRecords();
    }

    @Override
    public MedicalRecord findMedicalRecord(String firstName, String lastName) {
        return MedicalRecordsRepository.getMedicalRecords(firstName, lastName);
    }

    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {
        if(personService.findPerson(medicalRecord.getFirstName(), medicalRecord.getLastName())!=null){
            return MedicalRecordsRepository.addMedicalRecord(medicalRecord);
        }
        return null;
    }

    @Override
    public MedicalRecord update(MedicalRecord medicalRecord) {
        return MedicalRecordsRepository.updateMedicalRecords(medicalRecord);
    }

    @Override
    public boolean delete(String firstName, String lastName) {
        return MedicalRecordsRepository.deleteMedicalRecord(firstName, lastName);
    }

}
