package net.safety.safetalerts.service;

import net.safety.safetalerts.model.MedicalRecords;
import net.safety.safetalerts.repository.MedicalRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordsService {

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    public MedicalRecords getRecords(String firstName, String lastName) {
        return medicalRecordsRepository.findByName(firstName, lastName);

    }

    public void saveRecords(MedicalRecords currentRecords) {
        medicalRecordsRepository.save(currentRecords);
    }

    public void deleteRecords(String firstName, String lastName) {
        medicalRecordsRepository.deleteByRecords(firstName, lastName);
    }

    public List<MedicalRecords> getAllRecords(){
        return medicalRecordsRepository.getRecords();
    }



}
