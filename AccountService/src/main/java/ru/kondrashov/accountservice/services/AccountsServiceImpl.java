package ru.kondrashov.accountservice.services;

import ru.kondrashov.accountservice.entities.Account;
import ru.kondrashov.accountservice.repositories.AccountsRepository;

import java.util.Collection;
import java.util.UUID;

public class AccountsServiceImpl implements AccountsService{

    private final AccountsRepository accountsRepository;

    public AccountsServiceImpl(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }


    @Override
    public Collection<Account> getAccounts() {
        return accountsRepository.findAll();
    }

    @Override
    public Account getAccount(UUID id) {
        return accountsRepository.getOne(id);
    }

    @Override
    public void save(Account account) {
        accountsRepository.save(account);
    }

    @Override
    public void update(UUID id, Account account) {

    }

    @Override
    public void delete(UUID id) {
        accountsRepository.deleteById(id);
    }
}
