package controller;

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
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.url.ChildInfo;
import com.safetynet.alerts.model.url.PhoneInfo;
import com.safetynet.alerts.services.PersonService;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personServiceMock;

    @MockBean
    private MedicalRecord medicalRecordMock;

    @Test
    public void AlertController_ChildAlertTest() throws Exception {

        // GIVEN :
        ChildInfo childInfoMock = new ChildInfo();
        childInfoMock.setAge(10);
        childInfoMock.setFirstName("firstnametest");
        childInfoMock.setLastName("lastnametest");
        List<ChildInfo> listChildInfoMock = new ArrayList<>();
        listChildInfoMock.add(childInfoMock);
        Mockito.when(personServiceMock.getListChild(any(String.class))).thenReturn(listChildInfoMock);

        // WHEN //THEN return the medical record
        mockMvc.perform(get("/childAlert?address=adresstest")

                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$..firstName").value("firstnametest"))
                .andExpect(jsonPath("$..lastName").value("lastnametest"));

    }

    @Test
    public void AlertController_PhoneAlertTest() throws Exception {

        // GIVEN :
        PhoneInfo phoneInfoMock = new PhoneInfo();
        phoneInfoMock.setFirstName("firstnametest");
        phoneInfoMock.setLastName("lastnametest");
        phoneInfoMock.setPhone("phonetest");
        List<PhoneInfo> listPhoneInfoMock = new ArrayList<>();
        listPhoneInfoMock.add(phoneInfoMock);
        Mockito.when(personServiceMock.getListPhoneInfo(any(String.class))).thenReturn(listPhoneInfoMock);

        // WHEN //THEN return the medical record
        mockMvc.perform(get("/phoneAlert?firestation=1")

                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$..firstName").value("firstnametest"))
                .andExpect(jsonPath("$..lastName").value("lastnametest"))
                .andExpect(jsonPath("$..phone").value("phonetest"));

    }

}
