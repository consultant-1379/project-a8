package com.example.group4project.frontend_controllers;

import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.*;
import com.example.group4project.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TeamClientController {

    @Autowired
    RepositoryDAO repositoryDAO;

    @Autowired
    EmployeeRepository employeeRepository;

    @ModelAttribute("allEmployees")
    public List<Employee> populateEmployees()
    {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @RequestMapping("/")
    public String handler(Model model) {
        List<Team> teams = repositoryDAO.getAllTeams();
        model.addAttribute("teams", teams);
        return "myView";
    }

    @RequestMapping("/createNewTeam")
    public String createTeam(@RequestParam("name") String name,
                             Model model) {

        Team t = new Team(name);
        repositoryDAO.createTeam(t);
        model.addAttribute("team", t);
        return "teamSummary";
    }

    @RequestMapping("/removeTeam")
    public String removeTeam(@RequestParam("id") String id,
                             Model model) {

        Team t = repositoryDAO.deleteTeam(id);
        String teamName = t.getName();
        model.addAttribute("teamName", teamName);
        return "successfulTeamDeletion";
    }


    @RequestMapping("/showTeam")
    public String showTeam(Model model, @RequestParam("id") String id) {
        Optional<Team> teamOpt = repositoryDAO.getTeamById(id);
        if(teamOpt.isPresent()) {
            Team t = teamOpt.get();
            model.addAttribute("team", t);

            List<Employee> teamMembers = repositoryDAO.getTeamMembersForTeam(id);
            model.addAttribute("teamMembers", teamMembers);

            List<Retrospective> allRetrospectivesForTeam = repositoryDAO.getAllRetrospectivesForTeam(id);
            model.addAttribute("retrospectives", allRetrospectivesForTeam);

            return "teamPage";
        }
        else {
            System.out.println("Could not find team with id: " + id);
            return "myView";
        }
    }

    @RequestMapping("/removeRetrospective")
    public String removeRetrospective(@RequestParam("id") String id,
                                      @RequestParam("retroId") String retroId,
                             Model model) {

        model.addAttribute("id", id);
        Retrospective r = repositoryDAO.deleteRetrospective(retroId);
        String retroName = r.getName();
        model.addAttribute("retroName", retroName);

        Optional<Team> teamOpt = repositoryDAO.getTeamById(id);

        if(teamOpt.isPresent()) {
            Team t = teamOpt.get();
            String teamName = t.getName();
            model.addAttribute("teamName", teamName);
        }

        return "successfulRetrospectiveDeletion";
    }

    @RequestMapping("/viewEmployeeList")
    public String showEmployeesFromDatabase(@RequestParam("id") String id,
                                            Model model) {
        model.addAttribute("currentTeamId", id);

        List<Employee> allEmployees = repositoryDAO.getAllEmployees();
        for(Employee e: allEmployees) {
            System.out.println(e);
        }
        model.addAttribute("allEmployees", allEmployees);
        return "allEmployees";
    }


    @RequestMapping("/addNewMember")
    public String addNewMember(@RequestParam("teamId") String teamId,
                               @RequestParam("empId") String empId,
                               Model model) {

        TeamMember tm = new TeamMember(teamId, empId);
        // check if team member is already included in the team
        TeamMember thisTeamMember = repositoryDAO.findTeamMember(teamId, empId);
        if(thisTeamMember != null) {
            String message = "This employee is already part of your team";
            model.addAttribute("msg", message);
            model.addAttribute("currentTeamId", teamId);
            return "newTeamMemberFailed";
        }
        else {
            repositoryDAO.addTeamMember(tm);

            Optional<Employee> eOpt = employeeRepository.findById(empId);
            if (eOpt.isPresent()) {
                Employee e = eOpt.get();
                String name = e.getName();
                model.addAttribute("newMemberName", name);
            }
            model.addAttribute("currentTeamId", teamId);

            return "teamMemberConfirmation";
        }
    }

    @RequestMapping("/removeTeamMember")
    public String removeTeamMember(@RequestParam("id") String id,
                               @RequestParam("empId") String empId,
                               Model model) {

        TeamMember memberToDelete = repositoryDAO.deleteTeamMember(id, empId);
        Optional<Employee> thisMemberOpt = employeeRepository.findById(empId);
        if(thisMemberOpt.isPresent()) {
            Employee thisMember = thisMemberOpt.get();
            String name = thisMember.getName();
            model.addAttribute("teamMemberName", name);
        }

        model.addAttribute("currentTeamId", id);

        return "removeTeamMemberConfirmation";
    }

    @RequestMapping("/createNewRetrospective")
    public String createTeam(@RequestParam("teamId") String teamId,
                             @RequestParam("name") String name,
                             Model model) {

        model.addAttribute("currentTeamId", teamId);
        System.out.println(teamId);
        Retrospective r = new Retrospective(teamId, name);
        repositoryDAO.addRetrospective(r);
        model.addAttribute("retrospective", r);
        return "retrospectiveSummary";
    }

    @RequestMapping("/showRetrospective")
    public String showRetrospective(Model model, @RequestParam("retroId") String retroId,
                                    @RequestParam("teamId") String teamId) {

        Retrospective retrospective = repositoryDAO.getRetrospectiveById(retroId);
        model.addAttribute("retrospective", retrospective);

        List<Item> itemsForRetrospective = repositoryDAO.getItemsForRetrospective(retroId);
        model.addAttribute("items", itemsForRetrospective);

        return "retrospectivePage";
    }


}
