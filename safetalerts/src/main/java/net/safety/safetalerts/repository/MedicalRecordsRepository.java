package net.safety.safetalerts.repository;


import net.safety.safetalerts.model.MedicalRecords;

import java.util.List;

public interface MedicalRecordsRepository {

    void save(MedicalRecords currentRecords);

    void deleteByRecords(String firstName, String lastName);

    MedicalRecords findByName(String firstName, String lastName);

    List<MedicalRecords> getRecords();

    MedicalRecords findMedicalRecord(String firstName, String lastName);
}
