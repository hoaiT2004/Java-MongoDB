package com.example.springbootwithmongo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

@Data
@Builder
public class RegisterAccountResponse {
    private String id;
    private String name;
    private int age;
    @JsonIgnore
    private Date date;
}
