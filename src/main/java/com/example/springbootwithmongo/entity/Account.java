package com.example.springbootwithmongo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;//"2020-05-31T14:10:30.000Z"  || //"2020-05-31

@Document("account")
@Builder
@Data
public class Account {
    @Id
    @NotNull(message = "Please enter id")
    private String id;
    @NotNull(message = "Please enter username")
    private String name;

    @Min(value = 8)
    @Max(value = 99)
    private int age;

    private Date date;
}
