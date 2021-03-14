package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.url.CommunityEmail;
import com.safetynet.alerts.model.url.InfoPersonFull;
import com.safetynet.alerts.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;
    @MockBean
    private Person personMock;

    String firstNameConst = "firstnametest";
    String lastNameConst = "lastnametest";
    String addressConst = "addresstest";
    String cityConst = "citytest";
    String zipConst = "ziptest";
    String phoneConst = "phone";
    String emailConst = "emailtest@test.fr";

    @BeforeEach
    public void setUpEach() {

        personMock = new Person();
        personMock.setFirstName(firstNameConst);
        personMock.setLastName(lastNameConst);
        personMock.setAddress(addressConst);
        personMock.setCity(cityConst);
        personMock.setZip(zipConst);
        personMock.setPhone(phoneConst);
        personMock.setEmail(emailConst);

    }

    @Test
    public void PersonController_DeletePersonTest() throws Exception {

        // GIVEN

        Mockito.when(personService.delete(any(String.class), any(String.class))).thenReturn(true);
        // WHEN
        // THEN
        this.mockMvc
                .perform(delete("/person/delete?firstname=" + firstNameConst + "&lastname=" + lastNameConst)
                        .content(asJsonString(new Person(firstNameConst, lastNameConst, addressConst, cityConst,
                                zipConst, phoneConst, emailConst)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Deleted person : firstnametest lastnametest"))
                .andExpect(status().isOk());
    }

    @Test
    public void PersonController_PutPersonTest() throws Exception {

        // GIVEN

        Mockito.when(personService.update(any(Person.class))).thenReturn(personMock);
        // WHEN
        // THEN
        this.mockMvc
                .perform(put("/person/put")
                        .content(asJsonString(new Person(firstNameConst, lastNameConst, addressConst, cityConst,
                                zipConst, phoneConst, emailConst)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    @Test
    public void PersonController_addPersonTest() throws Exception {

        // GIVEN

        Mockito.when(personService.save(any(Person.class))).thenReturn(personMock);
        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/person/post")
                        .content(asJsonString(new Person(firstNameConst, lastNameConst, addressConst, cityConst,
                                zipConst, phoneConst, emailConst)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value(firstNameConst))
                .andExpect(jsonPath("$.lastName").value(lastNameConst));

    }

    @Test
    public void PersonController_addPerson_WithWrongEmailTest() throws Exception {

        // GIVEN

        Mockito.when(personService.save(any(Person.class))).thenReturn(personMock);
        // WHEN
        // THEN
        this.mockMvc.perform(post("/person/post")
                .content(asJsonString(new Person(firstNameConst, lastNameConst, addressConst, cityConst, zipConst,
                        phoneConst, "wrongEmail")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }


    @Test
    public void PersonController_PutNotExistingPersonTest() throws Exception {

        // GIVEN

        Mockito.when(personService.update(any(Person.class))).thenReturn(null);
        // WHEN
        // THEN
        this.mockMvc
                .perform(put("/person")
                        .content(asJsonString(new Person("firstNameNotExist", lastNameConst, addressConst, cityConst,
                                zipConst, phoneConst, emailConst)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }





    @Test
    public void CommunityEmailController_Test() throws Exception {

        // GIVEN :
        CommunityEmail communityEmailMock = new CommunityEmail();
        communityEmailMock.setEmail("emailtest");

        List<CommunityEmail> listCommunityEmail = new ArrayList<>();
        listCommunityEmail.add(communityEmailMock);
        Mockito.when(personService.getCommunityEmail(any(String.class))).thenReturn(listCommunityEmail);

        // WHEN
        // THEN
        mockMvc.perform(get("/communityEmail?city=citytest")

                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$..email").value("emailtest"));

    }

    @Test
    public void PersonInfoController_Test() throws Exception {

        // GIVEN :
        InfoPersonFull infoPersonMock = new InfoPersonFull();
        infoPersonMock.setFirstName("firstnametest");
        infoPersonMock.setLastName("lastnametest");
        infoPersonMock.setEmail("emailtest@email.us");
        infoPersonMock.setAge(10);
        infoPersonMock.setAddress("addresstest");

        List<InfoPersonFull> listInfoPersonMock = new ArrayList<>();
        listInfoPersonMock.add(infoPersonMock);

        Mockito.when(personService.getPersonInfo(any(String.class), any(String.class)))
                .thenReturn(listInfoPersonMock);

        // WHEN //THEN return the medical record
        mockMvc.perform(get("/personInfo?firstName=firstnametest&lastName=lastnametest")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$..firstName").value("firstnametest"))
                .andExpect(jsonPath("$..lastName").value("lastnametest"))
                .andExpect(jsonPath("$..email").value("emailtest@email.us")).andExpect(jsonPath("$..age").value(10))
                .andExpect(jsonPath("$..address").value("addresstest"));

    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
