package com.example.api.representingInheritance.JoinedClass;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_ta")
@PrimaryKeyJoinColumn(name = "user_id")
public class Ta extends User {
    private String noOfSessions;
    private double avgRating;
}
