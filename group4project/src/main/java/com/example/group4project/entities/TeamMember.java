package com.example.group4project.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="team_members")
public class TeamMember {

    @Id
    private String id;
    @Field("teamId")
    private String teamId;
    @Field("empId")
    private String empId;


    public TeamMember(String teamId, String empId) {
        this.teamId = teamId;
        this.empId = empId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
