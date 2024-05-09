package com.example.api.representingInheritance.SingleTable;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "3")
public class Ta extends User {
    private String noOfSessions;
    private double avgRating;
}
