package ru.kondrashov.personservice.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kondrashov.personservice.repositories.PeopleRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PeopleServiceImplTest {

    @Mock
    private PeopleRepository peopleRepository;

    @InjectMocks
    private PeopleServiceImpl peopleService;

    @Test
    void getAll() {

    }

    @Test
    void get() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}