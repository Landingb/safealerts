package Test;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.services.MedicalRecordService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
class MedicalRecordTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MedicalRecord medicalRecordMock;

    @MockBean
    MedicalRecordService medicalRecordService;

    final String FIRSTNAME = "firstnametest";
    final String LASTNAME = "lastnametest";
    final String PAYLOAD = "{\"firstname\":\"firstnametest\",\"lastname\":\"lastnametest\","
            + "\"birthdate\":\"\",\"medications\":[],\"allergies\":[]}";

    @Test
    public void GiveOneExistingMedicalRecordTest() throws Exception {

        medicalRecordMock = new MedicalRecord();
        medicalRecordMock.setFirstName(FIRSTNAME);
        medicalRecordMock.setLastName(LASTNAME);

        Mockito.when(medicalRecordService.findMedicalRecord(any(String.class), any(String.class)))
                .thenReturn(medicalRecordMock);

        // WHEN //THEN
        this.mockMvc
                .perform(get("/medicalrecord?firstname=" + FIRSTNAME + "&lastname=" + LASTNAME)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value(FIRSTNAME))
                .andExpect(jsonPath("$.lastName").value(LASTNAME));
    }

    @Test
    public void GiveNotExistingMedicalRecordTest() throws Exception {

        Mockito.when(medicalRecordService.findMedicalRecord(any(String.class), any(String.class)))
                .thenReturn(null);

        // WHEN //THEN
        this.mockMvc
                .perform(get("/medicalrecord?firstname=" + FIRSTNAME + "&lastname=" + LASTNAME)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void PostMedicalRecordTest() throws Exception {

        // GIVEN
        medicalRecordMock = new MedicalRecord();
        medicalRecordMock.setFirstName(FIRSTNAME);
        medicalRecordMock.setLastName(LASTNAME);
        Mockito.when(medicalRecordService.save(any(MedicalRecord.class))).thenReturn(medicalRecordMock);

        // WHEN //THEN return the station added
        this.mockMvc.perform(post("/firestation").content(PAYLOAD).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void PutMedicalRecordWithMedicalRecordExistingTest() throws Exception {

        // GIVEN
        medicalRecordMock = new MedicalRecord();
        medicalRecordMock.setFirstName(FIRSTNAME);
        medicalRecordMock.setLastName(LASTNAME);

        Mockito.when(medicalRecordService.update(any(MedicalRecord.class))).thenReturn(medicalRecordMock);

        // WHEN //THEN
        this.mockMvc
                .perform(put("/medicalrecord").content(PAYLOAD).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value(FIRSTNAME))
                .andExpect(jsonPath("$.lastName").value(LASTNAME));
    }

    @Test
    public void PutMedicalRecordWithMedicalRecordDotNotExistTest() throws Exception {
        // GIVEN
        Mockito.when(medicalRecordService.update(any(MedicalRecord.class))).thenReturn(null);

        // WHEN //THEN
        this.mockMvc.perform(put("/medicalrecord").content(PAYLOAD).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void deleteMedicalRecordWithMedicalRecordExistTest() throws Exception {
        // GIVEN

        Mockito.when(medicalRecordService.delete(any(String.class), any(String.class))).thenReturn(true);

        // WHEN //THEN
        this.mockMvc
                .perform(delete("/medicalrecord?firstname=" + FIRSTNAME + "&lastname=" + LASTNAME)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMedicalRecordWithMedicalRecordDoNotExistTest() throws Exception {
        // GIVEN

        Mockito.when(medicalRecordService.delete(any(String.class), any(String.class))).thenReturn(false);

        // WHEN //THEN
        this.mockMvc
                .perform(delete("/medicalrecord?firstname=" + FIRSTNAME + "&lastname=" + LASTNAME)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}