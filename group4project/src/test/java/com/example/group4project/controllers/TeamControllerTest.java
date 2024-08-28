package com.example.group4project.controllers;

import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.Team;
import com.example.group4project.services.TeamService;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TeamController.class)
@AutoConfigureMockMvc
public class TeamControllerTest {
    @MockBean
    TeamService teamService;

    @MockBean
    RepositoryDAO dao;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllEmployees() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/teams")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getTeamById() throws Exception{
        Optional<Team> testOP = Optional.of(new Team("test"));
        when(teamService.getTeamById("123")).thenReturn(testOP);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/teams/123")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void createTeam() throws Exception{
        Team newTeam = new Team("newTeam");
        when(teamService.createTeam(any())).thenReturn(newTeam);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/teams/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\" : \"newTeam\"}")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("ture",status == 200);
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void saveListTeams() throws Exception{
        List<Team> newTeams = new LinkedList<>();
        when(teamService.saveListTeams(any())).thenReturn(newTeams);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/teams/createmany")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"id\":\"5f85c0d6635b672c7eb775c8\",\"name\":\"UI-SDK2\",\"description\":\"creates another user interface\"},{\"id\":\"5f85ec5717e8092e35942f7c\",\"name\":\"UI-SDK\",\"description\":\"creates user interface\"},{\"id\":\"5f85ec5717e8092e35942f7d\",\"name\":\"Dynamo\",\"description\":\"works like dynamite\"},{\"id\":\"5f85ec5717e8092e35942f7e\",\"name\":\"cool kids\",\"description\":\"researches latest technologies\"},{\"id\":\"5f875252c85b3c6a5fa41bd2\",\"name\":\"Team 4\",\"description\":\"Develops a repository tool\"},{\"id\":\"5f8752df3aa6c42a876b6f4e\",\"name\":\"Team 5\",\"description\":\"Develops a data analytics tool\"}]")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("ture",status == 200);
        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updateCustomer() throws Exception{
        Optional<Team> testOP = Optional.of(new Team("test"));
        Team newTeam = testOP.get();
        when(teamService.updateTeam(anyString(),any())).thenReturn(newTeam);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.put("/teams/update/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\" : \"newName\"}")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void deleteTeamById() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.delete("/teams/deletebyid/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\" : \"testId\"}")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("ture",status == 200);
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }
    @Test
    public void deleteAllTeams() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.delete("/teams/deleteall")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }



}
