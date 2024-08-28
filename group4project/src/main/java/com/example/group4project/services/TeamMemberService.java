package com.example.group4project.services;

import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.Employee;
import com.example.group4project.entities.TeamMember;
import com.example.group4project.repositories.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMemberService {

    @Autowired
    TeamMemberRepository repository;

    @Autowired
    RepositoryDAO dao;

    public TeamMemberService() {}

    public List<TeamMember> getAllTeamMembers() {

        return dao.getAllTeamMembers();
    }

    public List<Employee> getTeamMembersForTeam(String teamid) {
        return dao.getTeamMembersForTeam(teamid);
    }

    public TeamMember findTeamMember(String teamid, String empid) {
        return dao.findTeamMember(teamid, empid);
    }

    public TeamMember addTeamMember(TeamMember tm) {
        return dao.addTeamMember(tm);
    }

    public TeamMember addTeamMember(String teamid, String empid) {
        return dao.addTeamMember(teamid, empid);
    }

    public void deleteTeamMemberById(String id) {
        repository.deleteById(id);
    }

    public TeamMember deleteTeamMember(String teamid, String empid) {

        return dao.deleteTeamMember(teamid, empid);
    }

    public List<TeamMember> deleteAllTeamMembers(String teamid) {
        return dao.deleteAllTeamMembers(teamid);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
