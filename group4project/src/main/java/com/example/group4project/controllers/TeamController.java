package com.example.group4project.controllers;

import com.example.group4project.dao.RepositoryDAO;
import com.example.group4project.entities.Team;
import com.example.group4project.exception.CustomException;
import com.example.group4project.response.ResponseMessage;
import com.example.group4project.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    RepositoryDAO dao;

    @GetMapping("")
    public ResponseEntity<List<Team>> getAllTeams() {

        List<Team> teams = teamService.getAllTeams();

        if(teams==null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok().body(teams);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable String id) {

        // get a document from MongoDB database using ID
        Optional<Team> teamOpt = teamService.getTeamById(id);

        if(teamOpt.isPresent()) {
            Team team = teamOpt.get();
            return ResponseEntity.ok().body(team);

        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @PostMapping("/create")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {

        // save to MongoDB database
        Team newTeam = teamService.createTeam(team);

        if(newTeam==null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(newTeam);
        }
    }

    @PostMapping("/createmany")
    public ResponseEntity<ResponseMessage> saveListTeams(@RequestBody List<Team> teams, HttpServletRequest request) {
        try {
            // save a list of customers to MongoDB database
            List newTeams = teamService.saveListTeams(teams);

            String message = "Successfully Uploaded a list of Teams to MongoDB";
            return new ResponseEntity(new ResponseMessage(message, request.getRequestURI(), newTeams), HttpStatus.OK);
        }catch(Exception e) {
            String message = "Can NOT upload a List of Teams to MongoDB database";
            return new ResponseEntity(new ResponseMessage(message, request.getRequestURI(),
                    e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Team> updateCustomer(@PathVariable String id, @RequestBody Team team){

        Team teamToUpdate = teamService.updateTeam(id, team);
        if(teamToUpdate != null) {
            return ResponseEntity.ok().body(teamToUpdate);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<ResponseMessage> deleteTeamById(@PathVariable String id, HttpServletRequest request) {
        try {
            // delete a Customer from MongoDB database using ID
            teamService.deleteTeamById(id);

            String message = "Successfully deleted a Team from MongoDB database with id = " + id;
            return new ResponseEntity<ResponseMessage>(new ResponseMessage(message, request.getRequestURI()), HttpStatus.OK);
        } catch(Exception e) {
            String message = "Can Not delete a Team from MongoDB database with id = " + id;
            return new ResponseEntity<ResponseMessage>(new ResponseMessage(message, request.getRequestURI(), e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<ResponseMessage> deleteAllTeams(HttpServletRequest request) {
        String message = "";
        try {
            teamService.deleteAll();

            message = "Successfully deleted all Teams from MongoDB database!";
            return new ResponseEntity<ResponseMessage> (new ResponseMessage(message,
                    request.getRequestURI()), HttpStatus.OK);
        } catch(Exception e) {
            message = "Can NOT delete All documents from MongoDB database";
            return new ResponseEntity<ResponseMessage> (new ResponseMessage(message,
                    request.getRequestURI(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
