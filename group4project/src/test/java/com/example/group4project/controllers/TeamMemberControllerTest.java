package com.example.group4project.controllers;

import com.example.group4project.entities.TeamMember;
import com.example.group4project.services.TeamMemberService;
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
@WebMvcTest(controllers = TeamMemberController.class)
@AutoConfigureMockMvc
public class TeamMemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TeamMemberService service;


    @Test
    public void getAllTeamMembers() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/team-members")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("ture",status == 200);
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getAllTeamMembersOneTeam() throws Exception{
        List<TeamMember> members = new LinkedList<>();
        when(service.getAllTeamMembers()).thenReturn(members);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/team-members/teamid")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("ture",status == 200);
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void findTeamMember() throws Exception{
        TeamMember member = new TeamMember("teamID","empID");
        when(service.findTeamMember(anyString(),anyString())).thenReturn(member);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/team-members/teamid/view/empid")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void addTeamMemberID() throws Exception{
        TeamMember member = new TeamMember("teamID","empID");
        when(service.addTeamMember(anyString(),anyString())).thenReturn(member);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/team-members/teamid/add/empid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"teamid\" : \"teamid\"} +" +
                                "{\"empid\" : \"empid\"} +")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("ture",status == 200);
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void addTeamMember() throws Exception{
        TeamMember newTeam = new TeamMember("teamID","empID");
        when(service.addTeamMember(any())).thenReturn(newTeam);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/team-members/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"teamId\":\"5f88af691cb8e125ff902bad\",\"empId\":\"5f88ad7bd48cd847c574e867\"}")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("ture",status == 200);
        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void deleteTeamById() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.delete("/team-members/delete/id")
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
    public void deleteTeamMember() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.delete("/team-members/teamid/delete/empid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"teamid\" : \"teamid\"} +" +
                                "{\"empid\" : \"empid\"} +")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("ture",status == 200);
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void deleteAllTeamMembers() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.delete("/team-members/deleteall")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.log()).andReturn();
//        System.out.println("OUTPUT" + mvcResult.getResponse().getContentAsString());
    }
}
