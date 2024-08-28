package com.example.group4project;

import com.example.group4project.dao.DataAccessInterface;
import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.*;
import com.example.group4project.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class SeedDb {

    public SeedDb() {}

    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private RepositoryDAO repositoryDAO;

    @PostConstruct
    public void init() {
        System.out.println("in seeddb init()");
        List<Employee> allEmployees = repository.findAll();


        if(allEmployees.size()==0) {
            repository.save(new Employee("John Kelly"));
            repository.save(new Employee("Mary Byrne"));
            repository.save(new Employee("Jim O'Rourke"));
            repository.save(new Employee("Patrick Delaney"));
            repository.save(new Employee("Martina O'Donnell"));
            repository.save(new Employee("Marie Dwyer"));
            repository.save(new Employee("Thomas Comerford"));
            repository.save(new Employee("Declan Sweeney"));
            repository.save(new Employee("Anna O'Connor"));
        }

        List<Team> allTeams = repositoryDAO.getAllTeams();
        System.out.println("length of all teams: " + allTeams.size());

        if(allTeams.size() == 0) {
            System.out.println("creating new teams now in the database....");
            // add teams to database:
            repositoryDAO.createTeam(new Team("Team1"));
            repositoryDAO.createTeam(new Team("Team2"));
            repositoryDAO.createTeam(new Team("Team3"));
            repositoryDAO.createTeam(new Team("Team4"));
            repositoryDAO.createTeam(new Team("Team5"));
            repositoryDAO.createTeam(new Team("Team6"));
            repositoryDAO.createTeam(new Team("Team7"));

            allTeams = repositoryDAO.getAllTeams();

            // now populate teams with data
            if(allTeams.size() != 0) {

                for(int i=0; i<allTeams.size(); i++) {
                    String teamId = allTeams.get(i).getId();
                    // adding the same employee as a team-member for all teams for now
                    String empId = allEmployees.get(0).getEmpId();
                    TeamMember tm = new TeamMember(teamId, empId);
                    repositoryDAO.addTeamMember(tm);
                }

                // creating a retrospective called Sprint 1 for every team
                for (Team t : allTeams) {
                    String id = t.getId();
                    repositoryDAO.addRetrospective(new Retrospective(id, "Sprint 1"));
                }

            }
        }
    }

/*	@PreDestroy
	public void cleanup() {
		repository.deleteAll();
	}
*/
}
