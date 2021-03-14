package com.safetynet.alerts.services;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalRecordsRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;



@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ServiceUtilTest {

    @Autowired
    ServiceUtil serviceUtil;

    @MockBean
    MedicalRecordsRepository medicalRecordsDAOMock;

    @Test
    void calculAgeJustMajorTest() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date d = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Date birthDay = addYears(d, -18);

        assertThat(serviceUtil.calculateAge(birthDay)).isEqualTo(18);
    }

    @Test
    void calculAgeMinorTest() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date d = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Date birthDay = addYears(d, -17);

        assertThat(serviceUtil.calculateAge(birthDay)).isEqualTo(17);
    }

    @Test
    void calculAgeJustBornTest() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date d = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Date birthDay = d;

        assertThat(serviceUtil.calculateAge(birthDay)).isEqualTo(0);
    }
    @Test
    public static Date addYears(Date date, int nb) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR,nb);
        return cal.getTime();
    }
    @Test
    void isNotChildByPersonTest() throws ParseException {
        // GIVEN
        Person person = new Person("firstname","lastname","","","","","");

        LocalDateTime localDateTime = LocalDateTime.now();
        Date d = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date birthDay = addYears(d, -19);
        List<String> medications = new ArrayList<String>();
        medications.add("");
        List<String> allergies = new ArrayList<String>();
        allergies.add("");
        MedicalRecord medicalRecordTest = new MedicalRecord("firstname","firstname",birthDay, medications, allergies);

        Mockito.when(medicalRecordsDAOMock.getMedicalRecords(any(String.class),any(String.class)))
                .thenReturn(medicalRecordTest);

        // WHEN
        // THEN
        assertThat(serviceUtil.isChild(person)).isFalse();
    }

    @Test
    void isChildByPersonTest() throws ParseException {
        // GIVEN
        Person person = new Person("firstname","lastname","","","","","");

        LocalDateTime localDateTime = LocalDateTime.now();
        Date d = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date birthDay = addYears(d, -18);
        List<String> medications = new ArrayList<String>();
        medications.add("");
        List<String> allergies = new ArrayList<String>();
        allergies.add("");

        MedicalRecord medicalRecordTest = new MedicalRecord("firstname","firstname",birthDay, medications, allergies);

        Mockito.when(medicalRecordsDAOMock.getMedicalRecords(any(String.class),any(String.class)))
                .thenReturn(medicalRecordTest);

        // WHEN
        // THEN
        assertThat(serviceUtil.isChild(person)).isTrue();
    }

    @Test
    void isNotChildByAgeTest() throws ParseException {
        // GIVEN
        // WHEN
        // THEN
        assertThat(serviceUtil.isChild(19)).isFalse();
    }

    @Test
    void isChildByAgeTest() throws ParseException {
        // GIVEN

        // WHEN
        // THEN
        assertThat(serviceUtil.isChild(18)).isTrue();
    }

    @Test
    void isChildByAgeForJustBornTest() throws ParseException {
        // GIVEN

        // WHEN
        // THEN
        assertThat(serviceUtil.isChild(0)).isTrue();
    }

}