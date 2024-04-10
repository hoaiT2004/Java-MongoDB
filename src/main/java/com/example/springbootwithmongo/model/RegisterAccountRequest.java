package com.example.springbootwithmongo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@Builder
public class RegisterAccountRequest {
    @NotNull(message = "Please enter id")
    private String id;
    @NotNull(message = "Please enter username")
    private String name;

    @Min(value = 8)
    @Max(value = 99)
    private int age;

    private Date date;
}
