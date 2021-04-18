package ru.kondrashov.accountservice.utils.interfacies;

import ru.kondrashov.accountservice.controllers.dto.AccountRequestDTO;
import ru.kondrashov.accountservice.controllers.dto.AccountResponseDTO;
import ru.kondrashov.accountservice.entities.Account;

public interface AccountMapping {

    AccountResponseDTO mapToAccountResponseDTO(Account account);

    Account mapToAccount(AccountRequestDTO accountRequestDTO);
}
