package Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
public class PersonInfoTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getinfoPersonWithFirstNameAndLastNameTest() throws Exception {
        // GIVEN
        // WHEN //THEN
        String body = "[{\"firstName\":\"Firstnametest1\",\"lastName\":\"Lastnametest1\","
                + "\"address\":\"adresstest1\",\"age\":19,\"email\":\"emailtest1@email.com\",\"medications\":[\"med1\"],"
                + "\"allergies\":[\"allergi1\"]}]";

        this.mockMvc
                .perform(get("/personInfo?firstName=Firstnametest1&lastName=Lastnametest1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(content().json(body)).andExpect(status().isOk());
    }

    @Test
    void getinfoPersonWithJustLastNameTest() throws Exception {
        // GIVEN
        // WHEN //THEN
        String body = "[{\"firstName\":\"Firstnametest1\",\"lastName\":\"Lastnametest1\",\"address\":\"adresstest1\","
                + "\"age\":19,\"email\":\"emailtest1@email.com\",\"medications\":[\"med1\"],\"allergies\":[\"allergi1\"]},"
                + "{\"firstName\":\"Firstnametest11\",\"lastName\":\"Lastnametest1\",\"address\":\"adresstest1\""
                + ",\"age\":0,\"email\":\"emailtest1@email.com\",\"medications\":null,\"allergies\":null}]";

        this.mockMvc.perform(get("/personInfo?firstName=&lastName=Lastnametest1").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().json(body)).andExpect(status().isOk());
    }

    @Test
    void getinfoPersonDoesNotExistTest() throws Exception {
        // GIVEN
        // WHEN //THEN

        this.mockMvc
                .perform(get("/infoPerson?firstname=firstnamenotexit&lastName=lastnamenotexist")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string("")).andExpect(status().isNotFound());
    }

}
