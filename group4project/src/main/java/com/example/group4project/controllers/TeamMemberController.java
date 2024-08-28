package com.example.group4project.controllers;

import com.example.group4project.entities.Employee;
import com.example.group4project.entities.TeamMember;
import com.example.group4project.response.ResponseMessage;
import com.example.group4project.services.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/team-members")
public class TeamMemberController {

    @Autowired
    TeamMemberService service;

    @GetMapping("")
    public ResponseEntity<List<TeamMember>> getAllTeamMembers() {

        // get all documents from MongoDB database
        List<TeamMember> members = service.getAllTeamMembers();

        if(members == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(members);
        }
    }

    @GetMapping("/{teamid}")
    public ResponseEntity<List<TeamMember>> getAllTeamMembers(@PathVariable String teamid) {

        // get all documents from MongoDB database
        List<TeamMember> members = service.getAllTeamMembers();

        if(members == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(members);
        }
    }


    @GetMapping("/{teamid}/view/{empid}")
    public TeamMember findTeamMember(@PathVariable String teamid, @PathVariable String empid) {
        return service.findTeamMember(teamid, empid);
    }


    @PostMapping("/{teamid}/add/{empid}")
    public TeamMember addTeamMember (@PathVariable String teamid, @PathVariable String empid) {
        return service.addTeamMember(teamid, empid);
    }

    @PostMapping("/create")
    public ResponseEntity<TeamMember> addTeamMember(@RequestBody TeamMember tm) {

        // save to MongoDB database
        TeamMember newMember = service.addTeamMember(tm);

        if(newMember==null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(newMember);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteTeamMemberById(@PathVariable String id, HttpServletRequest request) {
        try {
            // delete a Customer from MongoDB database using ID
            service.deleteTeamMemberById(id);

            String message = "Successfully deleted a TeamMember from MongoDB database with id = " + id;
            return new ResponseEntity<ResponseMessage>(new ResponseMessage(message, request.getRequestURI()), HttpStatus.OK);
        } catch(Exception e) {
            String message = "Can Not delete a TeamMember from MongoDB database with id = " + id;
            return new ResponseEntity<ResponseMessage>(new ResponseMessage(message, request.getRequestURI(), e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{teamid}/delete/{empid}")
    public TeamMember deleteTeamMember (@PathVariable String teamid, @PathVariable String empid) {
        return service.deleteTeamMember(teamid, empid);
    }


    @DeleteMapping("/deleteall")
    public ResponseEntity<ResponseMessage> deleteAllTeamMembers(HttpServletRequest request) {
        String message = "";
        try {
            service.deleteAll();

            message = "Successfully deleted all TeamMembers from MongoDB database!";
            return new ResponseEntity<ResponseMessage> (new ResponseMessage(message,
                    request.getRequestURI()), HttpStatus.OK);
        } catch(Exception e) {
            message = "Can NOT delete All documents from MongoDB database";
            return new ResponseEntity<ResponseMessage> (new ResponseMessage(message,
                    request.getRequestURI(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
