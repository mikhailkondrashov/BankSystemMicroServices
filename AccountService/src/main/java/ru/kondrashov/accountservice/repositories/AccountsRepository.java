package ru.kondrashov.accountservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kondrashov.accountservice.entities.Account;

import java.util.UUID;

public interface AccountsRepository extends JpaRepository<Account, UUID> {
}
