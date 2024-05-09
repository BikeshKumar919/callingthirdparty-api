package com.example.api.representingInheritance.TablePerClass;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ta extends User {
    private String noOfSessions;
    private double avgRating;
}
