package com.example.group4project.services;

import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.Employee;
import com.example.group4project.entities.TeamMember;
import com.example.group4project.repositories.TeamMemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeamMemberServiceTest {

    @Mock
    TeamMemberRepository repository;

    @Mock
    RepositoryDAO dao;

    @InjectMocks
    TeamMemberService service;

    @Test
    public void getAllTeamMembers(){
        List<TeamMember> teamMembers = new LinkedList<>();
        teamMembers.add(new TeamMember("testTID","testEMPID"));
        when(dao.getAllTeamMembers()).thenReturn(teamMembers);
        List<TeamMember> test = service.getAllTeamMembers();
        Assert.assertEquals("testEMPID",test.get(0).getEmpId());
    }

    @Test
    public void getTeamMembersForTeam(){
        List<Employee> employees = new LinkedList<>();
        employees.add(new Employee("testEMP"));
        when(dao.getTeamMembersForTeam("testTID")).thenReturn(employees);
        List<Employee> test = service.getTeamMembersForTeam("testTID");
        Assert.assertEquals("testEMP",test.get(0).getName());
    }

    @Test
    public void findTeamMember(){
        when(dao.findTeamMember("teamid", "empid")).thenReturn(new TeamMember("testTID","testEMPID"));
        TeamMember test = service.findTeamMember("teamid", "empid");
        Assert.assertEquals("testTID",test.getTeamId());
    }

    @Test
    public void addTeamMember(){
        when(dao.addTeamMember("testid","empid")).thenReturn(new TeamMember("testTID","testEMPID"));
        TeamMember test = service.addTeamMember("testid","empid");
        Assert.assertEquals("testTID",test.getTeamId());
    }

//    @Test
//    public void deleteTeamMemberById(){
//       doNothing().when(repository.deleteById(T));
//
//    }



}
