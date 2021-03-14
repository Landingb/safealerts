package com.safetynet.alerts.services;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordsRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
class MedicalRecordServiceTest {

    @MockBean
    PersonService personServiceMock;

    @MockBean
    MedicalRecordsRepositoryImpl medicalRecordsDAOMock;

    @MockBean
    ServiceUtil serviceUtil;

    @Autowired
    MedicalRecordService medicalRecordService;


    @Test
    void saveMedicalRecordWithExistingPerson() throws ParseException {
        // GIVEN
        Mockito.when(personServiceMock.findPerson(any(String.class), any(String.class)))
                .thenReturn(new ArrayList<>());
        Mockito.when(medicalRecordsDAOMock.addMedicalRecord(any(MedicalRecord.class)))
                .thenReturn(new MedicalRecord());

        String stringDate = "01/01/1990";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse(stringDate);
        List<String> medications = new ArrayList<String>();
        medications.add("");
        List<String> allergies = new ArrayList<String>();
        allergies.add("");

        MedicalRecord medicalRecordTest = new MedicalRecord("Firstnametest3","Lastnametest3",birthDay, medications, allergies);

        // WHEN
        // THEN
        assertThat(medicalRecordService.save(medicalRecordTest)).isNotNull();

    }
    @Test
    void saveMedicalRecordWithNotExistingPerson() throws ParseException {
        // GIVEN
        Mockito.when(personServiceMock.findPerson(any(String.class), any(String.class)))
                .thenReturn(null);
        Mockito.when(medicalRecordsDAOMock.addMedicalRecord(any(MedicalRecord.class)))
                .thenReturn(new MedicalRecord());

        String stringDate = "01/01/1990";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse(stringDate);
        List<String> medications = new ArrayList<String>();
        medications.add("");
        List<String> allergies = new ArrayList<String>();
        allergies.add("");

        MedicalRecord medicalRecordTest = new MedicalRecord("Firstnametest3","Lastnametest3",birthDay, medications, allergies);

        // WHEN
        // THEN
        assertThat(medicalRecordService.save(medicalRecordTest)).isNull();

    }

}

