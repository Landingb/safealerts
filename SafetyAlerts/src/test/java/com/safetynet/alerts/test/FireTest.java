package com.safetynet.alerts.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
class FireTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getFireListPersonWithExistingAddressTest() throws Exception {
        // GIVEN
        // WHEN //THEN
        String body = "[{\"firstName\":\"Firstnametest1\",\"lastName\":\"Lastnametest1\",\"age\":20,"
                + "\"phone\":\"phonetest1\",\"medications\":[\"med1\"],\"allergies\":[\"allergi1\"]},"
                + "{\"firstName\":\"Firstnametest11\",\"lastName\":\"Lastnametest1\",\"age\":0,"
                + "\"phone\":\"phonetest1\",\"medications\":null,\"allergies\":null}]";
        this.mockMvc.perform(get("/fire?address=adresstest1").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(content().string(body)).andExpect(status().isOk());
    }

    @Test
    void getFireListPersonWithNotExistingAddressTest() throws Exception {
        // GIVEN
        // WHEN //THEN
        this.mockMvc.perform(get("/fire?address=adresstestnotexist").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(content().string("[]")).andExpect(status().isOk());
    }

}