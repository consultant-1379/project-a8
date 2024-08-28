package com.example.group4project.services;

import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.Retrospective;
import com.example.group4project.repositories.RetrospectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetrospectiveService {

    @Autowired
    RetrospectiveRepository repository;

    @Autowired
    RepositoryDAO dao;

    public RetrospectiveService() {}

    public Retrospective addRetrospective(Retrospective r) {
        return dao.addRetrospective(r);
    }

    public Retrospective deleteRetrospective(String rId) {
        return dao.deleteRetrospective(rId);
    }

    public List<Retrospective> getAllRetrospectives() {
        return dao.getAllRetrospectives();
    }

    public List<Retrospective> getAllRetrospectivesForTeam(String teamid) {

        return dao.getAllRetrospectivesForTeam(teamid);
    }

    public Retrospective getRetrospectiveById(String rid) {
        return dao.getRetrospectiveById(rid);
    }

    public void deleteRetrospectiveById(String id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
