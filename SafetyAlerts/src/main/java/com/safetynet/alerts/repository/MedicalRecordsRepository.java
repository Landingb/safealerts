package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;

public interface MedicalRecordsRepository {
    public List<MedicalRecord> getAllMedicalRecords();
    public MedicalRecord getMedicalRecords(String firstName, String lastName);
    public void setAllMedicalRecords(List<MedicalRecord> listmedicalRecord);
    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);
    public MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord);
    public boolean deleteMedicalRecord (String firstName, String lastName);
}
