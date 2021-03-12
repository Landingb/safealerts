package Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.services.FireStationService;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
class ComplementTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    FireStationService fireStationService;

    @Test
    public void getCoverageFireStationTest() throws Exception {

        // GIVEN

        // WHEN //THEN
        String body = "{\"fireStationPersons\":[{\"firstName\":\"Firstnametest1\",\"address\":\"adresstest1\","
                + "\"phone\":\"phonetest1\",\"lastName\":\"Lastnametest1\"},{\"firstName\":\"Firstnametest11\","
                + "\"address\":\"adresstest1\",\"phone\":\"phonetest1\",\"lastName\":\"Lastnametest1\"}],"
                + "\"adultCount\":1,\"childCount\":1}";
        this.mockMvc.perform(get("/firestation?stationNumber=1").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(content().json(body));

    }
}