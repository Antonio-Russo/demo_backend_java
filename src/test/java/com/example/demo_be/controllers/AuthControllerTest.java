package com.example.demo_be.controllers;

import com.example.demo_be.models.JwtUser;
import com.example.demo_be.models.responses.JwtAuthResponse;
import com.example.demo_be.utils.JwtUtils;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebAppConfiguration
//@WebMvcTest(value= AuthController.class)

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtUtils jwtUtils;

    private final String username="antonioTest";
    private final String password="antonio";

    @Test
    //@WithMockUser(username = "antonioTest", password = "antonio", roles = "USER")
    public void createAuthenticationTokenTest() throws Exception {
        mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\""+username+"\",\"password\":\""+password+"\"}")
        ).andExpect(status().isOk());
    }

    @Test
    public void refreshAuthenticationTokenTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String token=createToken();
        ResultActions resultActions = mockMvc.perform(get("/refresh-token")
                .header("Authorization", "Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

        //recupero il contenuto del response e lo mappo nell'oggetto JwtAuthResponse
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        JwtAuthResponse response = mapper.readerFor(JwtAuthResponse.class).readValue(contentAsString);

        //controllo che ottengo un token valido
        JwtUser userDetails = jwtUtils.getUserDetails(response.getJwt());
        Assert.assertTrue(jwtUtils.validateToken(response.getJwt(),userDetails));

    }

    public String createToken() throws Exception {
        JwtUser userDetails = new JwtUser(username, password, new ArrayList<>(), true);
        String jwt = jwtUtils.generateToken(userDetails);
        return jwt;
    }



}