package ru.kondrashov.personservice.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.UUID;

@Entity
@Table(name = "people", schema = "public")
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotEmpty(message = "First name should not be empty")
    @Size(min = 1, max = 30)
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 1, max = 30)
    private String lastName;

    @Min(value = 0, message = "Age should be greater than 0")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;

    @NotEmpty(message = "Phone number should not be empty")
    private String phoneNumber;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;
}