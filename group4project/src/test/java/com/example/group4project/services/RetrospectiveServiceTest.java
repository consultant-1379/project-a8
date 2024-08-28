package com.example.group4project.services;

import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.Retrospective;
import com.example.group4project.repositories.RetrospectiveRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RetrospectiveServiceTest {
    @Mock
    RetrospectiveRepository repository;

    @Mock
    RepositoryDAO dao;

    @InjectMocks
    RetrospectiveService service;

    @Test
    public void addRetrospective(){
        when(dao.addRetrospective(any())).thenReturn(new Retrospective("test","test"));
        Retrospective test = service.addRetrospective(new Retrospective("testTeamID","testTameID"));
        Assert.assertEquals("test",test.getName());
    }

    @Test
    public void getAllRetrospectives(){
        when(dao.getAllRetrospectives()).thenReturn(new LinkedList<Retrospective>());
        List<Retrospective> test = service.getAllRetrospectives();
        Assert.assertEquals(0,test.size());
    }

    @Test
    public void getAllRetrospectivesForTeam(){
        when(dao.getAllRetrospectivesForTeam("testTID")).thenReturn(new LinkedList<Retrospective>());
        List<Retrospective> test = service.getAllRetrospectivesForTeam("testTID");
        Assert.assertEquals(0,test.size());
    }

    @Test
    public void getRetrospectiveById(){
        when(dao.getRetrospectiveById("testRID")).thenReturn(new Retrospective("testTeamID","testName"));
        Retrospective test = service.getRetrospectiveById("testRID");
        Assert.assertEquals("testName",test.getName());
    }

}
