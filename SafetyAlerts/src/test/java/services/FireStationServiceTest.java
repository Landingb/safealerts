package services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.safetynet.alerts.repository.FireStationRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.url.FloodHome;
import com.safetynet.alerts.model.url.InfoPersonFull;
import com.safetynet.alerts.services.FireStationService;
import com.safetynet.alerts.services.MedicalRecordService;
import com.safetynet.alerts.services.PersonService;
import com.safetynet.alerts.services.ServiceUtil;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
class FireStationServiceTest {

    @Autowired
    FireStationService fireStationTest;

    @MockBean
    FireStationRepository fireStationDAOMock;

    @MockBean
    PersonService personServiceMock;

    @MockBean
    MedicalRecordService medicalRecordServiceMock;

    @MockBean
    ServiceUtil serviceUtilMock;


    @Test
    void getFireStationCoverageTest() throws Exception {
        // GIVEN

        List<String> listAddressCoveredTest = new ArrayList<>();
        listAddressCoveredTest.add("address1");
        Mockito.when(fireStationDAOMock.getadressFireStationByStation(any(String.class))).thenReturn(listAddressCoveredTest);

        List<Person> listPersonTest = new ArrayList<>();
        Person person1 = new Person("firstname", "lastname", "", "City", "", "", "email1");
        Person person2 = new Person("firstname", "lastname", "", "City", "", "", "email2");
        listPersonTest.add(person1);
        listPersonTest.add(person2);
        Mockito.when(personServiceMock.findPersonByAddress(any(String.class))).thenReturn(listPersonTest);

        String stringDate = "01/01/1990";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse(stringDate);
        List<String> medications = new ArrayList<String>();
        medications.add("");
        List<String> allergies = new ArrayList<String>();
        allergies.add("");
        MedicalRecord medicalRecordTest = new MedicalRecord("Firstnametest3", "Lastnametest3", birthDay, medications,
                allergies);
        Mockito.when(medicalRecordServiceMock.findMedicalRecord(any(String.class), any(String.class)))
                .thenReturn(medicalRecordTest);

        // WHEN
        // THEN
        assertThat(fireStationTest.fireStationPersonsCovered("station").getChildCount()).isEqualTo(0);
        assertThat(fireStationTest.fireStationPersonsCovered("station").getAdultCount()).isEqualTo(2);
    }
    @Test
    void getFireListPersonTest() throws Exception {
        // GIVEN
        List<Person> listPersonTest = new ArrayList<>();
        Person person1 = new Person("firstname", "lastname", "", "City", "", "", "email1");
        Person person2 = new Person("firstname", "lastname", "", "City", "", "", "email2");
        listPersonTest.add(person1);
        listPersonTest.add(person2);
        Mockito.when(personServiceMock.findPersonByAddress(any(String.class))).thenReturn(listPersonTest);

        String stringDate = "01/01/1990";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse(stringDate);
        List<String> medications = new ArrayList<String>();
        medications.add("");
        List<String> allergies = new ArrayList<String>();
        allergies.add("");
        MedicalRecord medicalRecordTest = new MedicalRecord("Firstnametest3", "Lastnametest3", birthDay, medications,
                allergies);
        Mockito.when(medicalRecordServiceMock.findMedicalRecord(any(String.class), any(String.class)))
                .thenReturn(medicalRecordTest);

        Mockito.when(serviceUtilMock.calculateAge(any(Date.class))).thenReturn(10);

        // WHEN
        List<InfoPersonFull> listFireListPersonTest = fireStationTest.getFireListPerson("addresstest");

        // THEN
        assertThat(listFireListPersonTest.size()).isEqualTo(2);

    }

    @Test
    void getFloodListHomeTestTest() throws Exception {
        // GIVEN
        List<String> listadressTest = new ArrayList<>();
        String address1 = "Address1";
        String address2 = "Address2";
        listadressTest.add(address1);
        listadressTest.add(address2);
        Mockito.when(fireStationDAOMock.getadressFireStationByStation(any(String.class))).thenReturn(listadressTest);

        List<Person> listPersonTest = new ArrayList<>();
        Person person1 = new Person("firstname", "lastname", "", "City", "", "", "email1");
        Person person2 = new Person("firstname", "lastname", "", "City", "", "", "email2");
        listPersonTest.add(person1);
        listPersonTest.add(person2);
        Mockito.when(personServiceMock.findPersonByAddress(any(String.class))).thenReturn(listPersonTest);

        String stringDate = "01/01/1990";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse(stringDate);
        List<String> medications = new ArrayList<String>();
        medications.add("");
        List<String> allergies = new ArrayList<String>();
        allergies.add("");
        MedicalRecord medicalRecordTest = new MedicalRecord("Firstnametest3", "Lastnametest3", birthDay, medications,
                allergies);
        Mockito.when(medicalRecordServiceMock.findMedicalRecord(any(String.class), any(String.class)))
                .thenReturn(medicalRecordTest);

        Mockito.when(serviceUtilMock.calculateAge(any(Date.class))).thenReturn(10);

        // WHEN
        List<FloodHome> listFloodHomeTest = fireStationTest.getFloodListHome(listadressTest);

        // THEN
        assertThat(listFloodHomeTest.size()).isEqualTo(4);

    }

}