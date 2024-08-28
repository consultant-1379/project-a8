package com.example.group4project.dao;

import com.example.group4project.entities.*;
import com.example.group4project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RepositoryDAO implements DataAccessInterface {
    @Autowired
    EmployeeRepository empRepository;
    @Autowired
    RetrospectiveRepository retroRepository;
    @Autowired
    TeamMemberRepository tmRepository;
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ItemRepository itemRepository;

    public RepositoryDAO() {}

    @Override
    public List<Team> getAllTeams() {
        List<Team> allTeams = teamRepository.findAll();
        return allTeams;
    }

    @Override
    public Optional<Team> getTeamById(String teamid) {
        return teamRepository.findById(teamid);
    }


    @Override
    public Team createTeam(Team t) {
        return teamRepository.save(t);
    }

    @Override
    public Team deleteTeam(String id) {
        // for this method I want to delete everything associated with this team from the database
        // - Team Members
        // - Retrospectives
        // - Items corresponding to those retrospectives

        List<TeamMember> teamMembers = tmRepository.findByTeamId(id);
        for(TeamMember tm: teamMembers) {
            String tmId = tm.getId();
            tmRepository.deleteById(tmId);
        }

        List<Retrospective> teamRetrospectives = retroRepository.findByTeamId(id);
        for(Retrospective r: teamRetrospectives) {
            String rId = r.getRid();

            List<Item> retrospectiveItems = itemRepository.findByRetroId(rId);
            for(Item i: retrospectiveItems) {
                itemRepository.delete(i);
            }

            retroRepository.delete(r);
        }

        Optional<Team> team = teamRepository.findById(id);
        teamRepository.deleteById(id);
        if(team.isPresent()) {
            return team.get();
        }
        return null;
    }

    @Override
    public List<Employee> getTeamMembersForTeam(String teamid) {
        List<TeamMember> teamMembers = tmRepository.findByTeamId(teamid);
        List<Employee> employees = new ArrayList<>();

        for(TeamMember tm: teamMembers) {
            String employeeId = tm.getEmpId();
            Optional<Employee> e = empRepository.findById(employeeId);

            if(e.isPresent()) {
                employees.add(e.get());
            }
            else {
                System.out.println("Could not locate employee listed as team member for team id: " + teamid);
            }
        }
        return employees;
    }

    @Override
    public TeamMember addTeamMember(String teamid, String empid) {
        TeamMember tm = new TeamMember(teamid, empid);
        return tmRepository.save(tm);
    }

    @Override
    public TeamMember addTeamMember(TeamMember tm) {
        return tmRepository.save(tm);
    }

    @Override
    public List<TeamMember> getAllTeamMembers() {
        return tmRepository.findAll();
    }


    public TeamMember findTeamMember(String teamid, String empid) {
        Optional<TeamMember> tm = tmRepository.findByTeamIdAndEmpId(teamid, empid);
        if(tm.isPresent()) {
            return tm.get();
        }
        return null;
    }

    @Override
    public TeamMember deleteTeamMember(String teamid, String empid) {

        Optional<TeamMember> tmOpt = tmRepository.findByTeamIdAndEmpId(teamid, empid);
        if(tmOpt.isPresent()) {
            TeamMember tm = tmOpt.get();
            String tmId = tm.getId();
            tmRepository.deleteById(tmId);
            return tm;
        }
        else{
            System.out.println("Could not find team member by team id and employee id");
            return null;
        }

    }


    @Override
    public List<TeamMember> deleteAllTeamMembers(String teamid) {
        List<TeamMember> allTeamMembers = tmRepository.findByTeamId(teamid);

        if(allTeamMembers != null) {
            tmRepository.deleteByTeamId(teamid);
        }
        return allTeamMembers;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> allEmps = empRepository.findAll();
        return allEmps;
    }

    @Override
    public List<Retrospective> getAllRetrospectives() {
        List<Retrospective> allRetrospectives = retroRepository.findAll();
        return allRetrospectives;
    }

    @Override
    public List<Retrospective> getAllRetrospectivesForTeam(String teamid) {
        List<Retrospective> allTeamRetrospectives = retroRepository.findByTeamId(teamid);
        return allTeamRetrospectives;
    }

    @Override
    public Retrospective getRetrospectiveById(String rid) {
        Optional<Retrospective> rOpt = retroRepository.findById(rid);
        if(rOpt.isPresent()) {
            return rOpt.get();
        }
        return null;
    }

    @Override
    public Retrospective addRetrospective(Retrospective r) {
        return retroRepository.save(r);
    }

    public Retrospective deleteRetrospective(String retroId) {

        // for this method I want to delete everything associated with this retrospective from the database
        // - Retrospective Items

        List<Item> retrospectiveItems = itemRepository.findByRetroId(retroId);
        for(Item i: retrospectiveItems) {
            itemRepository.delete(i);
        }

        Optional<Retrospective> retroOpt = retroRepository.findByRid(retroId);

        if(retroOpt.isPresent()) {
            Retrospective r = retroOpt.get();
            retroRepository.delete(r);
            return r;
        }
        else {
            return null;
        }
    }

    @Override
    public List<Item> getItemsForRetrospective(String rid) {
        List<Item> items = itemRepository.findByRetroId(rid);
        return items;
    }

    public List<Item> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }

    public Item getByItemId(String itemId) {
        Optional<Item> itemOpt = itemRepository.findByItemId(itemId);
        if(itemOpt.isPresent()) {
            return itemOpt.get();
        }
        else {
            return null;
        }
    }

    @Override
    public Item addItemToRetrospective(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item deleteItem(String itemId) {
        Optional<Item> itemOpt = itemRepository.findByItemId(itemId);
        if(itemOpt.isPresent()) {
            Item i = itemRepository.deleteByItemId(itemId);
            return i;
        }
        else {
            return null;
        }
    }

    public List<Item> deleteAllItems() {

        List<Item> allItems = itemRepository.findAll();
        itemRepository.deleteAll();
        return allItems;
    }

    public List<Item> findItemsByCategory(String category) {
        List<Item> filteredItems = itemRepository.findByCategory(category);
        return filteredItems;
    }
}
