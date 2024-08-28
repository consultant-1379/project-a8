package com.example.group4project.services;

import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.Employee;
import com.example.group4project.entities.Team;
import com.example.group4project.exception.CustomException;
import com.example.group4project.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    TeamRepository repository;

    @Autowired
    RepositoryDAO dao;

    public TeamService() {}

    public List<Team> getAllTeams() {
        return dao.getAllTeams();
    }

    public Optional<Team> getTeamById(String id) {
        return dao.getTeamById(id);
    }

    public Team createTeam(Team team) {
        return dao.createTeam(team);
    }

    public List<Employee> getTeamMembersForTeam(String teamid) {
        return dao.getTeamMembersForTeam(teamid);
    }

    public List<Team> saveListTeams(List<Team> teams) {
        return repository.saveAll(teams);
    }


    public Team updateTeam(String id, Team team)  {
        Optional<Team> teamOpt = repository.findById(id);

        if(!teamOpt.isPresent()) {
            return null;
        }

        Team teamToModify = teamOpt.get();
        teamToModify.setName(team.getName());

        repository.save(teamToModify);
        return teamToModify;
    }

    public void deleteTeamById(String id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
