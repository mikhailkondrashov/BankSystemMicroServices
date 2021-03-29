package ru.kondrashov.personservice.services;

import org.springframework.stereotype.Service;
import ru.kondrashov.personservice.entities.Person;

import java.util.List;
import java.util.UUID;

public interface PeopleService {

    List<Person> getAll();
    Person get(UUID id);
    void save(Person person);
    void update(UUID id, Person person);
    void delete(UUID id);
}
