package ru.kondrashov.accountservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kondrashov.accountservice.entities.Payment;

import java.util.Collection;
import java.util.UUID;

public interface PaymentsRepository extends JpaRepository<Payment, UUID> {

    Collection<Payment> getPaymentsByBill_Id(UUID id);
}
