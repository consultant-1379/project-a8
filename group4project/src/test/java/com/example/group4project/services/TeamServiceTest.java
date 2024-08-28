package com.example.group4project.services;

import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.Employee;
import com.example.group4project.entities.Team;
import com.example.group4project.repositories.TeamRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest {

    @Mock
    TeamRepository repository;

    @Mock
    RepositoryDAO dao;

    @InjectMocks
    TeamService service;

    @Test
    public void getAllTeams(){
//        List<Team> teams = new LinkedList<>();
//        teams.add(new Team("test"));
//        when(dao.getAllTeamMembers()).thenReturn(teams);
//        List<Team> test = service.getAllTeams();
//        Assert.assertEquals("testEMPID",test.get(0));
    }

    @Test
    public void getTeamById(){
       when(dao.getTeamById("testTID")).thenReturn(Optional.of(new Team("test")));
       Optional<Team> test = service.getTeamById("testTID");
       Assert.assertEquals("test",test.get().getName());
    }

    @Test
    public void createTeam(){
        when(dao.createTeam(any())).thenReturn(new Team("testTeam"));
        Team test = service.createTeam(new Team("test"));
        Assert.assertEquals("testTeam",test.getName());
    }

    @Test
    public void getTeamMembersForTeam(){
        List<Employee> testTM = new LinkedList<Employee>();
        testTM.add(new Employee("test1"));
        testTM.add(new Employee("test2"));
        testTM.add(new Employee("test3"));
        when(dao.getTeamMembersForTeam("testTID")).thenReturn(testTM);
        List<Employee> test = service.getTeamMembersForTeam("testTID");
        Assert.assertEquals(3,test.size());
    }

    @Test
    public void saveListTeams(){
        when(repository.saveAll(new LinkedList<Team>())).thenReturn(new LinkedList<Team>());
        List<Team> test = service.saveListTeams(new LinkedList<Team>());
        Assert.assertEquals(0,test.size());
    }



}
