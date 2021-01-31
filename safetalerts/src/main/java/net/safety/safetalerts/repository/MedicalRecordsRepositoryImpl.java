package net.safety.safetalerts.repository;

import net.safety.safetalerts.Database;
import net.safety.safetalerts.model.MedicalRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordsRepositoryImpl implements MedicalRecordsRepository {

    @Autowired
    private Database database;

    @Override
    public void save(MedicalRecords currentRecords) {

    }

    @Override
    public void deleteByRecords(String firstName, String lastName) {

    }

    @Override
    public MedicalRecords findByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<MedicalRecords> getRecords(){
        return database.getRecords();
    }

    @Override
    public MedicalRecords findMedicalRecord(String firstName, String lastName) {
        return (MedicalRecords) database.getRecords();
    }
}
