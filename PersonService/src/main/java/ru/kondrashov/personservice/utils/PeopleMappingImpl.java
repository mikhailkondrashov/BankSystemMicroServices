package ru.kondrashov.personservice.utils;

import org.springframework.stereotype.Component;
import ru.kondrashov.personservice.controllers.dto.PersonDTO;
import ru.kondrashov.personservice.entities.Person;

@Component
public class PeopleMappingImpl implements PeopleMapping{

    public Person mapToPerson(PersonDTO personDTO){
        Person person = new Person();
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setAge(personDTO.getAge());
        person.setPhoneNumber(personDTO.getPhoneNumber());
        person.setEmail(personDTO.getEmail());
        return person;
    }

    public PersonDTO mapToPersonDTO(Person person){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName(     person.getFirstName());
        personDTO.setLastName(      person.getLastName());
        personDTO.setAge(           person.getAge());
        personDTO.setPhoneNumber(   person.getPhoneNumber());
        personDTO.setEmail(         person.getEmail());
        return personDTO;
    }

}
