package Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class FloodTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getFloodHomeWithExistingStationTest() throws Exception {
        // GIVEN
        // WHEN //THEN
        this.mockMvc.perform(get("/flood?stations=1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void getFloodHomeWithNotExistingStationTest() throws Exception {
        // GIVEN
        // WHEN //THEN
        this.mockMvc.perform(get("/flood?stations=nonexist").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[]")).andExpect(status().isOk());
    }
}
