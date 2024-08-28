package com.example.group4project.controllers;

import com.example.group4project.entities.Retrospective;
import com.example.group4project.services.RetrospectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retrospectives")
public class RetrospectiveController {

    @Autowired
    RetrospectiveService service;

    @GetMapping("")
    public ResponseEntity<List<Retrospective>> getAllRetrospectives() {

        // get all documents from MongoDB database
        List<Retrospective> allRetrospectives = service.getAllRetrospectives();

        if(allRetrospectives == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(allRetrospectives);
        }
    }

    @GetMapping("/team/{teamid}")
    public ResponseEntity<List<Retrospective>> getAllRetrospectivesForTeam(@PathVariable String teamid) {

        // get all documents from MongoDB database
        List<Retrospective> allRetrospectives = service.getAllRetrospectivesForTeam(teamid);

        if(allRetrospectives == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(allRetrospectives);
        }
    }

    @GetMapping("/{rid}")
    public ResponseEntity<Retrospective> getRetrospectiveById(@PathVariable String rid) {

        Retrospective r = service.getRetrospectiveById(rid);

        if(r == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(r);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<Retrospective> saveRetrospective(@RequestBody Retrospective r) {

        // save to MongoDB database
        Retrospective newRetrospective = service.addRetrospective(r);

        if(newRetrospective==null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(newRetrospective);
        }
    }

}
