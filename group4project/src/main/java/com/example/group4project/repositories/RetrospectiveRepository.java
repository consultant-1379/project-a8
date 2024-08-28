package com.example.group4project.repositories;

import com.example.group4project.entities.Retrospective;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RetrospectiveRepository extends MongoRepository<Retrospective, String> {

    @Query("{ 'teamId' : ?0 }")
    List<Retrospective> findByTeamId(String teamId);

    Optional<Retrospective> findByRid(String rId);

}
