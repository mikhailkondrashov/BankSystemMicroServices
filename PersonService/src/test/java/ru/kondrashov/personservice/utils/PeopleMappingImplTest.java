//package ru.kondrashov.personservice.utils;
//
//import org.junit.jupiter.api.Test;
//import ru.kondrashov.personservice.controllers.dto.PersonRequestDTO;
//import ru.kondrashov.personservice.controllers.dto.PersonResponseDTO;
//import ru.kondrashov.personservice.entities.Person;
//
//
//import java.time.LocalDate;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class PeopleMappingImplTest {
//
//    private PeopleMappingImpl peopleMapping = new PeopleMappingImpl();
//
//    @Test
//    void mapToPerson() {
//        PersonRequestDTO personRequestDTO = new PersonRequestDTO(
//                "Bill",
//                "Johnson",
//                LocalDate.of(2000,12,26),
//                "+7 123 258 54 85",
//                "BillJohnson@mail.com"
//        );
//
//        Person person = peopleMapping.mapToPerson(personRequestDTO);
//
//        assertEquals(person.getFirstName(),personRequestDTO.getFirstName());
//        assertEquals(person.getLastName(),personRequestDTO.getLastName());
//        assertEquals(person.getBirthdate(),personRequestDTO.getBirthdate());
//        assertEquals(person.getEmail(),personRequestDTO.getEmail());
//        assertEquals(person.getPhoneNumber(),personRequestDTO.getPhoneNumber());
//     }
//
//    @Test
//    void mapToPersonResponseDTO() {
//
//        Person person = new Person(
//                UUID.randomUUID(),
//                "Bill",
//                "Johnson",
//                LocalDate.of(2000,12,26),
//                "+7 123 258 54 85",
//                "BillJohnson@mail.com"
//        );
//
//        PersonResponseDTO personResponseDTO = peopleMapping.mapToPersonResponseDTO(person);
//
//        assertEquals(person.getFirstName(),     personResponseDTO.getFirstName());
//        assertEquals(person.getLastName(),      personResponseDTO.getLastName());
//        assertEquals(person.getBirthdate(),     personResponseDTO.getBirthdate());
//        assertEquals(person.getEmail(),         personResponseDTO.getEmail());
//        assertEquals(person.getPhoneNumber(),   personResponseDTO.getPhoneNumber());
//    }
//}