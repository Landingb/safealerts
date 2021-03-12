package controller;

import com.safetynet.alerts.model.url.FloodHome;
import com.safetynet.alerts.model.url.InfoPersonFull;
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

import static controller.PersonControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.url.FireStationCoverage;
import com.safetynet.alerts.services.FireStationService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    @MockBean
    private FireStation fireStationMock;

    @Test
    public void FirestationController_addANewFireStationTest() throws Exception {

        //GIVEN :
        fireStationMock = new FireStation();
        fireStationMock.setStation("stationtest");
        fireStationMock.setAddress("addresstest");
    }

    @Test
    public void FirestationController_updateFirestationTest() throws Exception {

        //GIVEN :
        fireStationMock = new FireStation();
        fireStationMock.setStation("stationtestmodified");
        fireStationMock.setAddress("addresstestmodified");
        Mockito.when(fireStationService.update(any(FireStation.class))).thenReturn(fireStationMock);

        //WHEN //THEN return the station added
        mockMvc.perform(put("/firestation")
                .content(asJsonString(new FireStation("stationtest","addresstest")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value("stationtestmodified"))
                .andExpect(jsonPath("$.address").value("addresstestmodified"));

    }

    @Test
    public void FirestationController_removeFireStationTest() throws Exception {

        //GIVEN :
        fireStationMock = new FireStation();
        fireStationMock.setStation("stationtest");
        fireStationMock.setAddress("addresstest");
        List<FireStation> listFireStation = new ArrayList<>();
        listFireStation.add(fireStationMock);
        Mockito.when(fireStationService.delete(any(FireStation.class))).thenReturn(listFireStation);

        //WHEN //THEN return the station added
        mockMvc.perform(delete("/firestation")
                .content(asJsonString(new FireStation("stationtest","addresstest")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..station").value("stationtest"));

    }

    @Test
    public void FirestationController_getCoverageFireStationTest() throws Exception {

        //GIVEN :
        FireStationCoverage fireStationCoverageMock = new FireStationCoverage();
        fireStationCoverageMock.setAdultCount(1);
        fireStationCoverageMock.setChildCount(2);
        Mockito.when(fireStationService.fireStationPersonsCovered(any(String.class))).thenReturn(fireStationCoverageMock);

        //WHEN //THEN return the station added
        mockMvc.perform(get("/firestation?stationNumber=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..adultCount").value(1))
                .andExpect(jsonPath("$..childCount").value(2));

    }

    @Test
    public void FireController_Test() throws Exception {

        // GIVEN :
        InfoPersonFull fireListPersonMock = new InfoPersonFull();
        fireListPersonMock.setAge(10);
        fireListPersonMock.setFirstName("firstnametest");
        fireListPersonMock.setLastName("lastnametest");
        fireListPersonMock.setPhone("phonetest");
        fireListPersonMock.setStation("stationtest");

        List<InfoPersonFull> listFireListPerson = new ArrayList<>();
        listFireListPerson.add(fireListPersonMock);
        Mockito.when(fireStationService.getFireListPerson(any(String.class))).thenReturn(listFireListPerson);

        // WHEN
        // THEN
        mockMvc.perform(get("/fire?address=addresstest").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$..firstName").value("firstnametest"))
                .andExpect(jsonPath("$..lastName").value("lastnametest")).andExpect(jsonPath("$..age").value(10))
                .andExpect(jsonPath("$..phone").value("phonetest"));

    }

    @Test
    public void FloodController_Test() throws Exception {

        // GIVEN :
        FloodHome floodHomeMock = new FloodHome();
        floodHomeMock.setAddress("addresstest");

        List<FloodHome> listFloodHomeMock = new ArrayList<>();
        listFloodHomeMock.add(floodHomeMock);
        List<String> listStation = new ArrayList<>();
        listStation.add("1");
        Mockito.when(fireStationService.getFloodListHome(listStation)).thenReturn(listFloodHomeMock);

        // WHEN //THEN return the medical record
        mockMvc.perform(
                get("/flood?stations=1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$..address").value("addresstest"));

    }

}