package ru.kondrashov.accountservice.services;

import org.springframework.stereotype.Service;
import ru.kondrashov.accountservice.entities.Account;

import java.util.Collection;
import java.util.UUID;


public interface AccountsService {

    Collection<Account> getAccounts();
    Account getAccount(UUID id);
    void save(Account account);
    void update(UUID id, Account account);
    void delete(UUID id);
}
