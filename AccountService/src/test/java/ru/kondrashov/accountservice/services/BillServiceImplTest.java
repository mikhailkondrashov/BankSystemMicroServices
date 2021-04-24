package ru.kondrashov.accountservice.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kondrashov.accountservice.repositories.BillsRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BillServiceImplTest {

    @Mock
    private BillsRepository billsRepository;

    @InjectMocks
    BillServiceImpl billService;



}