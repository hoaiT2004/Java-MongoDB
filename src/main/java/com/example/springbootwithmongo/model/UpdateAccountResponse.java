package com.example.springbootwithmongo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;


@Data
@Builder
public class UpdateAccountResponse {

    private String name;
    private int age;
    @JsonIgnore
    private Date date;
}
