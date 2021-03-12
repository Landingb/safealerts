package com.safetynet.alerts.services;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;

public interface MedicalRecordService {
    public List<MedicalRecord> findAll();

    public MedicalRecord findMedicalRecord(String firstName, String lastName);
    
    MedicalRecord save(MedicalRecord medicalRecord);

    MedicalRecord update(MedicalRecord medicalRecord);

    boolean delete(String firstName, String lastName);
}
