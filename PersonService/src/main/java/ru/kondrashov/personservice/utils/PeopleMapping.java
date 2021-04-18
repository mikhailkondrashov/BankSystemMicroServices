package ru.kondrashov.personservice.utils;

import org.springframework.stereotype.Component;
import ru.kondrashov.personservice.controllers.dto.PersonRequestDTO;
import ru.kondrashov.personservice.controllers.dto.PersonResponseDTO;
import ru.kondrashov.personservice.entities.Person;

public interface PeopleMapping {

    PersonResponseDTO mapToPersonResponseDTO(Person person);

    Person mapToPerson(PersonRequestDTO personRequestDTO);

}
