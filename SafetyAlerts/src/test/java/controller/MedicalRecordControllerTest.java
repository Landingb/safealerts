package controller;

import org.junit.jupiter.api.BeforeEach;
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


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.services.MedicalRecordService;

import static controller.PersonControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
class MedicalRecordControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordServiceMock;
    @MockBean
    private MedicalRecord medicalRecordMock;

    String firstNameConst = "firstnametest";
    String lastNameConst = "lastnametest";
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    List<String> medicationsListConst;
    List<String> allergiesListConst;
    String lastNameInexistingConst = "Invisible Man";

    @BeforeEach
    public void setUpEach() throws ParseException {

        medicationsListConst = new ArrayList<>(Arrays.asList("hydroxychloroquine", "azithromycine"));
        allergiesListConst = new ArrayList<>(Arrays.asList("peanut", "shellfish"));

        Date birthDay = dateFormat.parse("01/01/2001");

        medicalRecordMock = new MedicalRecord();
        medicalRecordMock.setFirstName(firstNameConst);
        medicalRecordMock.setLastName(lastNameConst);
        medicalRecordMock.setBirthdate(birthDay);
        medicalRecordMock.setAllergies(allergiesListConst);
        medicalRecordMock.setMedications(medicationsListConst);
    }

    @Test
    public void MedicalRecordController_addANewMedicalRecord_Test() throws Exception {

        // GIVEN :
        Date birthDay = dateFormat.parse("01/01/2001");

        Mockito.when(medicalRecordServiceMock.save(any(MedicalRecord.class))).thenReturn(medicalRecordMock);

        // WHEN //THEN return the medical record
        mockMvc.perform(post("/medicalrecord")
                .content(asJsonString(new MedicalRecord(firstNameConst, lastNameConst, birthDay, allergiesListConst,
                        medicationsListConst)))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstNameConst))
                .andExpect(jsonPath("$.lastName").value(lastNameConst));

    }

    @Test
    public void MedicalRecordController_updateMedicalRecord_Test() throws Exception {

        // GIVEN :
        Date birthDay = dateFormat.parse("01/01/2001");

        Mockito.when(medicalRecordServiceMock.update(any(MedicalRecord.class))).thenReturn(medicalRecordMock);

        // WHEN //THEN return the medical record
        mockMvc.perform(put("/medicalrecord")
                .content(asJsonString(new MedicalRecord(firstNameConst, lastNameConst, birthDay, allergiesListConst,
                        medicationsListConst)))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$..firstName").value(firstNameConst))
                .andExpect(jsonPath("$..lastName").value(lastNameConst));

    }

    @Test
    public void MedicalRecordController_deleteMedicalRecord_Test() throws Exception {

        // GIVEN :
        Date birthDay = dateFormat.parse("01/01/2001");

        Mockito.when(medicalRecordServiceMock.delete(any(String.class), any(String.class))).thenReturn(true);

        // WHEN //THEN return the medical record
        mockMvc.perform(delete("/medicalrecord?firstname=" + firstNameConst + "&lastname=" + lastNameConst)
                .content(asJsonString(new MedicalRecord(firstNameConst, lastNameConst, birthDay, allergiesListConst,
                        medicationsListConst)))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void MedicalRecordController_getMedicalRecord_Test() throws Exception {

        // GIVEN :
        Date birthDay = dateFormat.parse("01/01/2001");

        Mockito.when(medicalRecordServiceMock.findMedicalRecord(any(String.class), any(String.class)))
                .thenReturn(medicalRecordMock);

        // WHEN //THEN return the medical record
        mockMvc.perform(get("/medicalrecord?firstname=" + firstNameConst + "&lastname=" + lastNameConst)
                .content(asJsonString(new MedicalRecord(firstNameConst, lastNameConst, birthDay, allergiesListConst,
                        medicationsListConst)))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$..firstName").value(firstNameConst))
                .andExpect(jsonPath("$..lastName").value(lastNameConst));

    }

}