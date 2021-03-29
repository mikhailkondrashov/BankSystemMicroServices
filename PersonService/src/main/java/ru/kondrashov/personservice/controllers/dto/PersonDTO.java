package ru.kondrashov.personservice.controllers.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class PersonDTO {

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
