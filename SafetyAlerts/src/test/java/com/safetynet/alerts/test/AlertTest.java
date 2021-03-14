package com.safetynet.alerts.test;

import org.junit.jupiter.api.Test;
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
class AlertTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void ChildAlertTest() throws Exception {
        // GIVEN

        // WHEN //THEN
        String body = "[{\"firstName\":\"Firstnametestchildinfo\",\"lastName\":\"Lastnametestchildinfo\","
                + "\"age\":1,\"houseMembers\":[{\"firstName\":\"Firstnametestchildinfo\",\"lastName\":\"Lastnametestchildinfo\","
                + "\"address\":\"adresstestchildinfo\",\"city\":\"Citytest1\",\"zip\":\"ziptest1\",\"phone\":\"phonetest1\","
                + "\"email\":\"emailtest1@email.com\"}]}]";
        this.mockMvc.perform(get("/childAlert?address=adresstestchildinfo").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().json(body)).andExpect(status().isOk());
    }

    @Test
    void ChildAlertWithoutChildTest() throws Exception {
        // GIVEN

        // WHEN //THEN
        String body = "[]";
        this.mockMvc.perform(get("/childAlert?address=adresstest1").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().json(body)).andExpect(status().isOk());
    }



    @Test
    void PhoneAlertWithNotExistingStationTest() throws Exception {
        // GIVEN

        // WHEN //THEN
        String body = "[]";
        this.mockMvc.perform(get("/phoneAlert?firestation=10").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(content().json(body)).andExpect(status().isOk());
    }

}
