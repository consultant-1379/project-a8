package com.example.group4project.repositories;

import com.example.group4project.entities.TeamMember;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamMemberRepository extends MongoRepository<TeamMember, String> {

    List<TeamMember> findByTeamId(String teamId);

    @Query("{ 'teamId' : ?0 , 'empId' : ?1}")
    Optional<TeamMember> findByTeamIdAndEmpId(String teamid, String empid);


    void deleteByTeamId(String teamid);
}
