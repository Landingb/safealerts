package net.safety.safetalerts;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.safety.safetalerts.model.FireStations;
import net.safety.safetalerts.model.MedicalRecords;
import net.safety.safetalerts.model.Persons;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class Database {

    private List<FireStations> stations;
    private List<Persons> personsL;
    private List<MedicalRecords> records;

    @PostConstruct
    public void parsing() throws IOException, ParseException {

        ObjectMapper mapper = new ObjectMapper();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("C:\\Git\\ProjetOc\\safetalerts\\src\\main\\resources\\data.json"));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray personsList = (JSONArray) jsonObject.get("persons");
        JSONArray recordList = (JSONArray) jsonObject.get("medicalrecords");
        JSONArray stationList = (JSONArray) jsonObject.get("firestations");

        this.records =
                Arrays.asList(mapper.readValue(recordList.toString(), MedicalRecords[].class));
        this.personsL =
                Arrays.asList(mapper.readValue(personsList.toString(), Persons[].class));
        this.stations =
                Arrays.asList(mapper.readValue(stationList.toString(), FireStations[].class));

        for (Persons person : personsL) {
            for (FireStations fireStation : stations) {
                if (person.getAddress().equals(fireStation.getAddress())) {
                    person.setFireStations(fireStation);
                    fireStation.getPersons().add(person);
                }
            }
        }

        for (Persons person : personsL) {
            for (MedicalRecords record : records) {
                if (person.getLastName().equals(record.getLastName()) && person.getFirstName().equals(record.getFirstName())) {
                    person.setMedicalRecords(record);
                }
            }
        }
        System.out.println(personsL.toString());
    }

    public List<FireStations> getStations() {
        return stations;
    }

    public void setStations(List<FireStations> stations) {
        this.stations = stations;
    }

    public List<Persons> getPersonsL() {
        return personsL;
    }

    public void setPersonsL(List<Persons> personsL) {
        this.personsL = personsL;
    }

    public List<MedicalRecords> getRecords() {
        return records;
    }

    public void setRecords(List<MedicalRecords> records) {
        this.records = records;
    }
}



