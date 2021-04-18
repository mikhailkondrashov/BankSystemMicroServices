package ru.kondrashov.accountservice.services.interfacies;

import org.springframework.stereotype.Service;
import ru.kondrashov.accountservice.entities.Account;

import java.util.Collection;
import java.util.UUID;


public interface AccountsService {

    Collection<Account> getAccounts();
    Collection<Account> getAccountsByPersonId(UUID id);
    Account getAccount(UUID id);
    void save(Account account);
    void update(UUID id, Account account);
    void delete(UUID id);
}
