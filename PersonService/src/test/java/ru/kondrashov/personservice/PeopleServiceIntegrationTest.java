//package ru.kondrashov.personservice;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import ru.kondrashov.personservice.controllers.dto.PersonRequestDTO;
//import ru.kondrashov.personservice.entities.Person;
//import ru.kondrashov.personservice.repositories.PeopleRepository;
//import ru.kondrashov.personservice.services.PeopleService;
//
//import java.time.LocalDate;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//@TestPropertySource(locations = "/hibernate.properties")
//public class PeopleServiceIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private PeopleRepository peopleRepository;
//
//    @Autowired
//    private PeopleService peopleService;
//
//    private void init() throws Exception{
//
//        String name = "Mike";
//        String lastName = "Tyson";
//        LocalDate birthdate = LocalDate.of(1966, 06, 30);
//        String phoneNumber = "+7 1234859520";
//        String email = "miketyson@mail.com";
//
//        PersonRequestDTO personRequestDTO = new PersonRequestDTO(name, lastName, birthdate, phoneNumber, email);
//
//        mockMvc.perform(
//                post("/v1/people")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(personRequestDTO)))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void createPerson() throws Exception{
//
//        String name = "Mike";
//        String lastName = "Tyson";
//        LocalDate birthdate = LocalDate.of(1966, 06, 30);
//        String phoneNumber = "+7 1234859520";
//        String email = "miketyson@mail.com";
//
//        init();
//
//        Person person = peopleRepository.findAll().get(0);
//
//        assertThat(person.getBirthdate().isEqual(birthdate)).isTrue();
//        assertThat(person.getEmail()).isEqualToIgnoringCase(email);
//        assertThat(person.getFirstName()).isEqualToIgnoringCase(name);
//        assertThat(person.getLastName()).isEqualToIgnoringCase(lastName);
//        assertThat(person.getPhoneNumber()).isEqualToIgnoringCase(phoneNumber);
//
//        peopleService.delete(person.getId());
//    }
//
//    @Test
//    void update() throws Exception{
//
//        init();
//
//        Person person = peopleRepository.findAll().get(0);
//
//        String name = "John";
//        String lastName = "Jones";
//        LocalDate birthdate = LocalDate.of(1987, 07, 19);
//        String phoneNumber = "+7 9876543210";
//        String email = "JohnJones@mail.com";
//
//        person.setFirstName(name);
//        person.setLastName(lastName);
//        person.setPhoneNumber(phoneNumber);
//        person.setEmail(email);
//        person.setBirthdate(birthdate);
//
//        mockMvc.perform(
//                put("/v1/people/{id}", person.getId())
//                        .content(objectMapper.writeValueAsString(person))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isAccepted());
//
//        assertThat(person.getBirthdate().isEqual(birthdate)).isTrue();
//        assertThat(person.getEmail()).isEqualToIgnoringCase(email);
//        assertThat(person.getFirstName()).isEqualToIgnoringCase(name);
//        assertThat(person.getLastName()).isEqualToIgnoringCase(lastName);
//        assertThat(person.getPhoneNumber()).isEqualToIgnoringCase(phoneNumber);
//
//        peopleService.delete(person.getId());
//    }
//
//    @Test
//    void delete() throws Exception{
//        init();
//        Person person = peopleRepository.findAll().get(0);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.delete("/v1/people/{id}", person.getId())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
//}
