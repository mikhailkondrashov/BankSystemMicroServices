package ru.kondrashov.personservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kondrashov.personservice.entities.Person;

import java.util.UUID;

@Repository
public interface PeopleRepository extends JpaRepository<Person, UUID> {

    Person getPersonById(UUID id);

}
