package ru.kondrashov.personservice.utils;

import org.springframework.stereotype.Component;
import ru.kondrashov.personservice.controllers.dto.PersonRequestDTO;
import ru.kondrashov.personservice.controllers.dto.PersonResponseDTO;
import ru.kondrashov.personservice.entities.Person;

@Component
public class PeopleMappingImpl implements PeopleMapping{

    @Override
    public Person mapToPerson(PersonRequestDTO personRequestDTO){
        Person person = new Person();
        person.setFirstName(personRequestDTO.getFirstName());
        person.setLastName(personRequestDTO.getLastName());
        person.setBirthdate(personRequestDTO.getBirthdate());
        person.setPhoneNumber(personRequestDTO.getPhoneNumber());
        person.setEmail(personRequestDTO.getEmail());
        return person;
    }

    @Override
    public PersonResponseDTO mapToPersonResponseDTO(Person person){
        PersonResponseDTO personResponseDTO = new PersonResponseDTO();
        personResponseDTO.setId(            person.getId());
        personResponseDTO.setFirstName(     person.getFirstName());
        personResponseDTO.setLastName(      person.getLastName());
        personResponseDTO.setBirthdate(     person.getBirthdate());
        personResponseDTO.setPhoneNumber(   person.getPhoneNumber());
        personResponseDTO.setEmail(         person.getEmail());
        return personResponseDTO;
    }

}
