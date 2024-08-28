package com.example.group4project.dao;

import com.example.group4project.entities.*;


import java.util.List;
import java.util.Optional;

public interface DataAccessInterface {

    List<Team> getAllTeams();
    Optional<Team> getTeamById(String teamid);
    Team createTeam(Team t);
    Team deleteTeam(String id);
    List<Employee> getTeamMembersForTeam(String teamid);

    TeamMember addTeamMember(String teamid, String empid);
    TeamMember addTeamMember(TeamMember tm);

    TeamMember deleteTeamMember(String teamid, String empid);

    List<TeamMember> getAllTeamMembers();   // probably won't be used (unless useful to display)


    List<TeamMember> deleteAllTeamMembers(String teamid);


    List<Employee> getAllEmployees();


    List<Retrospective> getAllRetrospectives();
    List<Retrospective> getAllRetrospectivesForTeam(String teamid);
    Retrospective getRetrospectiveById(String rid);
    Retrospective addRetrospective(Retrospective r);

    List<Item> getItemsForRetrospective(String rid);
    Item addItemToRetrospective(Item item);

    Item createItem(Item item);
}
