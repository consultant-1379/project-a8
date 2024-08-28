package com.example.group4project.controllers;


import com.example.group4project.entities.Retrospective;
import com.example.group4project.services.RetrospectiveService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RetrospectiveController.class)
@AutoConfigureMockMvc
public class RetrospectiveControllerTest {

    @MockBean
    RetrospectiveService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllRetrospectives() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/retrospectives")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
//        System.out.println("OUTPUT1" + mvcResult.getResponse().getContentAsString());
    }


    @Test
    public void getAllRetrospectivesForTeam() throws Exception{
        List<Retrospective> newRetrospectives = new LinkedList<>();
        when(service.getAllRetrospectivesForTeam(anyString())).thenReturn(newRetrospectives);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/retrospectives/team/teamid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }
    @Test
    public void getRetrospectiveById() throws Exception{
        Retrospective newRest = new Retrospective("teamid","name");
        when(service.getRetrospectiveById(anyString())).thenReturn(newRest);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/retrospectives/rid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void saveRetrospective() throws Exception{
        Retrospective newRetro = new Retrospective("teamid","newTeam");
        when(service.addRetrospective(any())).thenReturn(newRetro);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/retrospectives/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"teamid\" : \"teamid\"},{\"name\": \"name\"}")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("ture",status == 200);
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }

}


