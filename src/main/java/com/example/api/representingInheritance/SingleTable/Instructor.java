package com.example.api.representingInheritance.SingleTable;


import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue(value = "1")
public class Instructor extends User {
    private String spec;
}
