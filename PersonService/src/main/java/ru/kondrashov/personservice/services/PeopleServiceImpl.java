package ru.kondrashov.personservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kondrashov.personservice.entities.Person;
import ru.kondrashov.personservice.exception.PersonNotFoundException;
import ru.kondrashov.personservice.repositories.PeopleRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PeopleServiceImpl implements PeopleService{
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> getAll(){
        return peopleRepository.findAll();
    }

    public Person get(UUID id){
        return peopleRepository.
                getPersonById(id).
                orElseThrow( () -> new PersonNotFoundException("Person with id = "+id+" not found"));
    }

    public void save(Person person){
        peopleRepository.save(person);
    }

    public void update(UUID id, Person changedPerson) {
        Person originPerson = get(id);
        originPerson.setFirstName(changedPerson.getFirstName());
        originPerson.setLastName(changedPerson.getLastName());
        originPerson.setBirthdate(changedPerson.getBirthdate());
        originPerson.setPhoneNumber(changedPerson.getPhoneNumber());
        originPerson.setEmail(changedPerson.getEmail());

        peopleRepository.save(originPerson);

    }

    public void delete(UUID id){
        peopleRepository.delete(get(id));
    }

}
