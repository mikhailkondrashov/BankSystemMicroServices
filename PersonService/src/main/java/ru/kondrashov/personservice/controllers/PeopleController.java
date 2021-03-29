package ru.kondrashov.personservice.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kondrashov.personservice.controllers.dto.PersonDTO;
import ru.kondrashov.personservice.services.PeopleService;
import ru.kondrashov.personservice.services.PeopleServiceImpl;
import ru.kondrashov.personservice.utils.PeopleMapping;
import ru.kondrashov.personservice.utils.PeopleMappingImpl;


import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/people")
@Api(description = "Controller which provides methods for operations with people")
public class PeopleController {

    private final PeopleService peopleService;
    private final PeopleMapping peopleMapping;

    @Autowired
    public PeopleController(PeopleServiceImpl peopleService, PeopleMappingImpl peopleMapping) {
        this.peopleService = peopleService;
        this.peopleMapping = peopleMapping;
    }

    @GetMapping(value = "")
    public Collection<PersonDTO> getPeople(){

        return peopleService.getAll()
                .stream()
                .map(peopleMapping::mapToPersonDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable UUID id){

        return peopleMapping.mapToPersonDTO(peopleService.get(id));
    }

    @PostMapping()
    public void createPerson(@RequestBody @Valid PersonDTO personDTO){

       peopleService.save(peopleMapping.mapToPerson(personDTO));

    }

    @PatchMapping("/{id}")
    public void updatePerson(@RequestBody @Valid PersonDTO personDTO, @PathVariable("id") UUID id) {
        peopleService.update(id, peopleMapping.mapToPerson(personDTO));
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") UUID id){
        peopleService.delete(id);

    }
}
