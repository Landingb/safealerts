package Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class ListTestObjects {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void GiveAllPersonTest() throws Exception {

        // WHEN //THEN

        this.mockMvc.perform(get("/persons")).andExpect(status().isOk());
    }

    @Test
    public void GiveAllMedicalRecordTest() throws Exception {

        // WHEN //THEN

        this.mockMvc.perform(get("/medicalrecords")).andExpect(status().isOk());
    }
    @Test
    public void GiveAllFireStationTest() throws Exception {

        // WHEN //THEN

        this.mockMvc.perform(get("/firestations")).andExpect(status().isOk());
    }
}
