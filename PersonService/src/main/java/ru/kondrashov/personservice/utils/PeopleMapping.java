package ru.kondrashov.personservice.utils;

import org.springframework.stereotype.Component;
import ru.kondrashov.personservice.controllers.dto.PersonDTO;
import ru.kondrashov.personservice.entities.Person;

@Component
public interface PeopleMapping {

    Person mapToPerson(PersonDTO personDTO);
    PersonDTO mapToPersonDTO(Person person);
}
