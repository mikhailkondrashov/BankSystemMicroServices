package ru.kondrashov.accountservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kondrashov.accountservice.entities.Bill;

import java.util.UUID;

public interface BillsRepository extends JpaRepository<Bill, UUID> {
}
