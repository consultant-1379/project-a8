package com.example.group4project.repositories;

import com.example.group4project.entities.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<Team,String> {

    // not sure yet about this
    List<Team> findTeamByName(String name);

}
