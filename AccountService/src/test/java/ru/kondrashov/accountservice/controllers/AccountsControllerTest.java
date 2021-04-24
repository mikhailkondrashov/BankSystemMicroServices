package ru.kondrashov.accountservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.kondrashov.accountservice.controllers.dto.AccountRequestDTO;
import ru.kondrashov.accountservice.entities.Account;
import ru.kondrashov.accountservice.repositories.AccountsRepository;
import ru.kondrashov.accountservice.services.AccountsServiceImpl;
import ru.kondrashov.accountservice.services.interfacies.AccountsService;
import ru.kondrashov.accountservice.utils.AccountMappingImpl;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountsController.class)
class AccountsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountsServiceImpl accountsService;
    @MockBean
    private AccountMappingImpl accountMapping;

    @Test
    void whenValidUrlAndMethodAndContentType_thenReturns201() throws Exception {

        AccountRequestDTO account = new AccountRequestDTO("name",LocalDate.now(), UUID.randomUUID());

        mockMvc.perform(post("/v1/people/{personId}/accounts", UUID.randomUUID())
                .content(objectMapper.writeValueAsString(account))
                .contentType("application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    void whenValidInput_thenMapsToBusinessModel() throws Exception {

        UUID uuid = UUID.randomUUID();
        LocalDate localDate = LocalDate.now();

        Account account = new Account("name",localDate,uuid );

        mockMvc.perform(post("/v1/people/{personId}/accounts", uuid)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isCreated());

        ArgumentCaptor<Account> userCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountsService, times(1)).save(userCaptor.capture());
        assertThat(userCaptor.getValue().getName()).isEqualTo("name");
        assertThat(userCaptor.getValue().getPersonId()).isEqualTo(uuid);
        assertThat(userCaptor.getValue().getCreationDate()).isEqualTo(localDate);


    }




}