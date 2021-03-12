package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.MedicalRecord;

@Repository
public class MedicalRecordsRepositoryImpl implements MedicalRecordsRepository {
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    @Override
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecords;
    }

    @Override
    public MedicalRecord getMedicalRecords(String firstName, String lastName) {

        for (MedicalRecord m : medicalRecords) {
            if (m.getFirstName().contentEquals(firstName) && m.getLastName().contentEquals(lastName)) {
                return m;
            }
        }
        return null;
    }


    @Override
    public MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord) {
        int index = 0;

        for (MedicalRecord m : medicalRecords) {
            if (m.getFirstName().contentEquals(medicalRecord.getFirstName())
                    && m.getLastName().contentEquals(medicalRecord.getLastName())) {
                medicalRecords.set(index, medicalRecord);
                return medicalRecord;
            }
            index++;
        }

        return null;
    }

    @Override
    public boolean deleteMedicalRecord(String firstName, String lastName) {
        List<MedicalRecord> recordsFind = new ArrayList<>();

        for (MedicalRecord m :medicalRecords){
            if(m.getFirstName().contentEquals(firstName)&&(m.getLastName().contentEquals(lastName))){
                recordsFind.add(m);
            }
        }
        if(recordsFind.size()>0){
            medicalRecords.removeAll(recordsFind);
            return true;
        }

        return false;
    }


    @Override
    public void setAllMedicalRecords(List<MedicalRecord> listmedicalRecord) {
        this.medicalRecords = listmedicalRecord;

    }

    @Override
    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
        return medicalRecord;
    }

}
