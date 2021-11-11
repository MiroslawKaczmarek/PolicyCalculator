package com.example.policycalculator.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import javax.transaction.Transactional;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.inject.Inject;

@AutoConfigureMockMvc
@SpringBootTest
public class PolicyControllerTest {

    @Inject
    private MockMvc mockMvc;

    @Transactional
    @Test
    public void calculateScenarioOk() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/policy/1/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                     .andExpect(content().string("17.13"));
    }

    @Transactional
    @Test
    public void calculateError1() throws Exception {
        mockMvc.perform(get("/policy/4/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Not exist PolicyStrategy with TYPE=abcUnknown. This type is used in sub objects of Policy (policy ID: 4)."));
    }

    @Transactional
    @Test
    public void calculateError2() throws Exception {
        mockMvc.perform(get("/policy/5/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Condition (grater than two thousand) used in PolicyStrategy record (type=FLOOD) is not correct. "+
                        "It must be in format <operator><value> - for example: >=14.55"));
    }

    @Transactional
    @Test
    public void getPolicyNotFound() throws Exception {
        mockMvc.perform(get("/policy/99999")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Not exist Policy with ID: 99999."));
    }

    @Transactional
    @Test
    public void getPolicyOk() throws Exception {
        mockMvc.perform(get("/policy/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value("PolicyNumber-XB-112"))
                .andExpect(jsonPath("$['policyObjects']", hasSize(1)))
                .andExpect(jsonPath("$['policyObjects'][0]['policySubObjects']", hasSize(2)))
                .andExpect(jsonPath("$['policyObjects'][0]['policySubObjects'][1].sumInsured").value("8"));
    }

}
