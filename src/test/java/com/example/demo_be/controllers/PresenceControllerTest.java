package com.example.demo_be.controllers;

import com.example.demo_be.dto.PresenceDTO;
import com.example.demo_be.entities.Presence;
import com.example.demo_be.models.JwtUser;
import com.example.demo_be.models.responses.JwtAuthResponse;
import com.example.demo_be.utils.JwtUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PresenceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtUtils jwtUtils;

    private final String username="antonioTest";
    private final String password="antonio";

    @Test
    void listPresences() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String token=createToken();
        ResultActions resultActions = mockMvc.perform(post("/listpresences")
                .header("Authorization-token", "Bearer "+token)
                .param("idTelegram", "111")
                .param("year", "2020")
                .param("month", "8")
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

        //recupero il contenuto del response e lo mappo nell'oggetto PresenceDTO
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<PresenceDTO> presenze = mapper.readValue(contentAsString, new TypeReference<List<PresenceDTO>>(){});

        Assert.assertNotNull(presenze);
        Assert.assertEquals(2,presenze.size());

    }

    //todo:da implementare test
    @Test
    void addPresence() {
    }

    //todo:da implementare test
    @Test
    void deletePresence() {
    }

    public String createToken() throws Exception {
        JwtUser userDetails = new JwtUser(username, password, new ArrayList<>(), true);
        String jwt = jwtUtils.generateToken(userDetails);
        return jwt;
    }
}