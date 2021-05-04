package ru.kondrashov.accountservice.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kondrashov.accountservice.entities.Payment;
import ru.kondrashov.accountservice.repositories.PaymentsRepository;
import ru.kondrashov.accountservice.services.interfaces.PaymentsService;

import java.util.Collection;
import java.util.UUID;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    private final PaymentsRepository paymentsRepository;

    public PaymentsServiceImpl(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    @Override
    public Collection<Payment> getPaymentsByBillId(UUID billId) {
        return paymentsRepository.getPaymentsByBill_Id(billId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Payment payment) {
        paymentsRepository.save(payment);
    }


}
