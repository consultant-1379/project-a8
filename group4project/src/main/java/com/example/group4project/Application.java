package com.example.group4project;

import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.Team;
import com.example.group4project.services.EmployeeService;
import com.example.group4project.services.RetrospectiveService;
import com.example.group4project.services.TeamMemberService;
import com.example.group4project.services.TeamService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Application.class, args);

		RepositoryDAO dao = context.getBean(RepositoryDAO.class);
		TeamService teamservice = context.getBean(TeamService.class);
		EmployeeService employeeService = context.getBean(EmployeeService.class);
		TeamMemberService tmservice = context.getBean(TeamMemberService.class);
		RetrospectiveService retroService = context.getBean(RetrospectiveService.class);

		System.out.println();
		//String lastId = "";
		for(Team t: dao.getAllTeams()) {
			System.out.println(t.getId() + ":\t" + t.getName() );
			//lastId=t.getId();
		}



//		for(Team t: teamservice.getAllTeams()) {
//			System.out.println(t.getId() + " " + t.getName() + ": " + t.getDescription());
//		}
		System.out.println(employeeService.getAllEmployees());
		System.out.println();
		System.out.println(tmservice.getAllTeamMembers());
		System.out.println();

	}


}
