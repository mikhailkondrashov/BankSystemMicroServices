package ru.kondrashov.personservice.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.kondrashov.personservice.controllers.dto.PersonRequestDTO;
import ru.kondrashov.personservice.controllers.dto.PersonResponseDTO;
import ru.kondrashov.personservice.services.PeopleService;
import ru.kondrashov.personservice.services.PeopleServiceImpl;
import ru.kondrashov.personservice.utils.PeopleMapping;
import ru.kondrashov.personservice.utils.PeopleMappingImpl;


import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/people/v1", produces = MediaType.APPLICATION_JSON_VALUE)
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
    @ResponseStatus(HttpStatus.OK)
    public Collection<PersonResponseDTO> getPeople(){

        return peopleService.getAll()
                .stream()
                .map(peopleMapping::mapToPersonResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonResponseDTO getPerson(@PathVariable UUID id){
        return peopleMapping.mapToPersonResponseDTO(peopleService.get(id));
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPerson(@RequestBody @Valid PersonRequestDTO personRequestDTO){
       peopleService.save(peopleMapping.mapToPerson(personRequestDTO));
    }

    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePerson(@RequestBody @Valid PersonRequestDTO personRequestDTO, @PathVariable("id") UUID id) {
        peopleService.update(id, peopleMapping.mapToPerson(personRequestDTO));
    }

    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable("id") UUID id){
        peopleService.delete(id);

    }
}
