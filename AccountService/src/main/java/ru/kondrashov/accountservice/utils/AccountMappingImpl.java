package ru.kondrashov.accountservice.utils;

import org.springframework.stereotype.Component;
import ru.kondrashov.accountservice.controllers.dto.AccountRequestDTO;
import ru.kondrashov.accountservice.controllers.dto.AccountResponseDTO;
import ru.kondrashov.accountservice.entities.Account;
import ru.kondrashov.accountservice.utils.interfacies.AccountMapping;

@Component
public class AccountMappingImpl implements AccountMapping {

    public AccountResponseDTO mapToAccountResponseDTO(Account account){
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();

        accountResponseDTO.setId(           account.getId());
        accountResponseDTO.setName(         account.getName());
        accountResponseDTO.setCreationDate( account.getCreationDate());
        accountResponseDTO.setPersonId(     account.getPersonId());
        return accountResponseDTO;
    }

    @Override
    public Account mapToAccount(AccountRequestDTO accountRequestDTO) {
        Account account = new Account();

        account.setName(accountRequestDTO.getName());
        account.setCreationDate(accountRequestDTO.getCreationDate());
        account.setPersonId(accountRequestDTO.getPersonId());

        return account;
    }


}
