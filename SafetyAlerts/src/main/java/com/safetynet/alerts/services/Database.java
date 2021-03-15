package com.safetynet.alerts.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@Component
public class Database {

    private List<FireStation> stations;
    private List<Person> personsL;
    private List<MedicalRecord> records;

    @PostConstruct
    public void parsing() throws IOException, ParseException, org.json.simple.parser.ParseException {

        ObjectMapper mapper = new ObjectMapper();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/main/resources/data.json"));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray personsList = (JSONArray) jsonObject.get("persons");
        JSONArray recordList = (JSONArray) jsonObject.get("medicalrecords");
        JSONArray stationList = (JSONArray) jsonObject.get("firestations");

        this.records =
                Arrays.asList(mapper.readValue(recordList.toString(), MedicalRecord[].class));
        this.personsL =
                Arrays.asList(mapper.readValue(personsList.toString(), Person[].class));
        this.stations =
                Arrays.asList(mapper.readValue(stationList.toString(), FireStation[].class));

       /* for (Person person : personsL) {
            for (FireStation fireStation : stations) {
                if (person.getAddress().equals(fireStation.getAddress())) {
                    person.setStation();
                    fireStation.getPersons().add(person);
                }
            }
        }

        for (Person person : personsL) {
            for (MedicalRecord record : records) {
                if (person.getLastName().equals(record.getLastName()) && person.getFirstName().equals(record.getFirstName())) {
                    person.setRecord(recordList);
                }
            }
        }*/
        System.out.println(personsL.toString());
    }

    public List<FireStation> getStations() {
        return stations;
    }

    public void setStations(List<FireStation> stations) {
        this.stations = stations;
    }

    public List<Person> getPersonsL() {
        return personsL;
    }

    public void setPersonsL(List<Person> personsL) {
        this.personsL = personsL;
    }

    public List<MedicalRecord> getRecords() {
        return records;
    }

    public void setRecords(List<MedicalRecord> records) {
        this.records = records;
    }
}



