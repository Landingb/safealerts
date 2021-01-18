package net.safety.safetalerts;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.safety.safetalerts.model.FireStations;
import net.safety.safetalerts.model.MedicalRecords;
import net.safety.safetalerts.model.Persons;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;

import java.util.Arrays;
import java.util.List;

public class Database {
    public static void main (String []args){
        parsing();
    }

    public static void parsing(){
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("C:\\Git\\ProjetOc\\safetalerts\\src\\main\\resources\\data.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray personsList = (JSONArray) jsonObject.get("persons");
            JSONArray recordList = (JSONArray) jsonObject.get("medicalrecords");
            JSONArray stationList = (JSONArray) jsonObject.get("firestations");

            // convert JSON array to list of medicalRecords
            List<MedicalRecords> records =
                    Arrays.asList(mapper.readValue(recordList.toString(), MedicalRecords[].class));
            List<Persons> persons=
                    Arrays.asList(mapper.readValue(personsList.toString(), Persons[].class));
            List<FireStations> stations =
                    Arrays.asList(mapper.readValue(stationList.toString(),FireStations[].class));

            // print medicalRecords
            System.out.println(records.toString());
            System.out.println(stations.toString());
            System.out.println(persons.toString());

        } catch (Exception e) {
            e.printStackTrace();
    }

    }





/*    private ObjectMapper objectMapper;
    List<FireStations> fireStations;

    String jsonCarArray =
            "[{ \"address\" : \" \", \"firestations\" : \" \" }]";

    {
        try {
            fireStations = objectMapper.readValue(jsonCarArray, new TypeReference<List<FireStations>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }*/

}
