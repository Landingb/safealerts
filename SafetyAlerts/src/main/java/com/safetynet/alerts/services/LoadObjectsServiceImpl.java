package com.safetynet.alerts.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordsRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.ObjectsSafetyAlert;
import com.safetynet.alerts.model.Person;

@Service
public class LoadObjectsServiceImpl implements LoadObjectsService {
    private static final Logger logger = LogManager.getLogger("LoadPersonsService");

    @Value("${filename}")
    private String fileName;

    @Autowired
    PersonRepository personRepository;
    
    @Autowired
    FireStationRepository fireStationRepository;
    
    @Autowired
    MedicalRecordsRepository medicalRecordsRepository;

    List<Person> listPerson = new ArrayList<Person>();
    List<FireStation> listFireStation = new ArrayList<FireStation>();
    List<MedicalRecord> listmedicalRecord = new ArrayList<MedicalRecord>();

    @PostConstruct
    @Override
    public void loadAlertObjects() throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        try {
            ObjectsSafetyAlert object = mapper.readValue(new File(fileName), ObjectsSafetyAlert.class);

            listPerson = object.getPersons();
            listFireStation = object.getFirestations();
            listmedicalRecord = object.getMedicalrecords();

            personRepository.setAllPersons(listPerson);
            fireStationRepository.setAllFireStations(listFireStation);
            medicalRecordsRepository.setAllMedicalRecords(listmedicalRecord);

            logger.info("Loaded Json objects");

        } catch (JsonMappingException e) {
            e.printStackTrace();
            logger.error("Error Mapping JSON", e);
        } catch (JsonParseException e) {
            e.printStackTrace();
            logger.error("Error parsing JSON", e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Error inupt/output JSON", e);
        }

    }

}
