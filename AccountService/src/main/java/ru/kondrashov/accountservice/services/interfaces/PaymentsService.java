package ru.kondrashov.accountservice.services.interfaces;


import ru.kondrashov.accountservice.entities.Payment;

import java.util.Collection;
import java.util.UUID;

public interface PaymentsService {

    void save(Payment payment);

    Collection<Payment> getPaymentsByBillId(UUID billId);
}
