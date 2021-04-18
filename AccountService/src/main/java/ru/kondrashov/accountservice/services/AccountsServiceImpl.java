package ru.kondrashov.accountservice.services;

import org.springframework.stereotype.Service;
import ru.kondrashov.accountservice.entities.Account;
import ru.kondrashov.accountservice.exceptions.AccountNotFoundException;
import ru.kondrashov.accountservice.repositories.AccountsRepository;
import ru.kondrashov.accountservice.services.interfacies.AccountsService;

import java.util.Collection;
import java.util.UUID;

@Service
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;

    public AccountsServiceImpl(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }


    @Override
    public Collection<Account> getAccounts() {
        return accountsRepository.findAll();
    }

    @Override
    public Collection<Account> getAccountsByPersonId(UUID id) {
        return accountsRepository.getAccountsByPersonId(id);
    }

    @Override
    public Account getAccount(UUID id) {
        return accountsRepository.getAccountsById(id).orElseThrow(()-> new AccountNotFoundException("Account with id = "+id+" not found."));
    }

    @Override
    public void save(Account account) {
        accountsRepository.save(account);
    }

    @Override
    public void update(UUID id, Account account) {
        Account updatedAccount = getAccount(id);

        updatedAccount.setName(account.getName());
        updatedAccount.setCreationDate(account.getCreationDate());
        updatedAccount.setPersonId(account.getPersonId());

        accountsRepository.save(updatedAccount);
    }

    @Override
    public void delete(UUID id) {
        accountsRepository.delete(getAccount(id));
    }
}
