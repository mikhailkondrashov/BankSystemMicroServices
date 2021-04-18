package ru.kondrashov.accountservice.utils;

import org.springframework.stereotype.Component;
import ru.kondrashov.accountservice.controllers.dto.FinancialTransactionResponseDTO;
import ru.kondrashov.accountservice.controllers.dto.PaymentRequestDTO;
import ru.kondrashov.accountservice.controllers.dto.PaymentResponseDTO;
import ru.kondrashov.accountservice.entities.Payment;
import ru.kondrashov.accountservice.utils.interfacies.PaymentsMapping;

@Component
public class PaymentsMappingImpl implements PaymentsMapping {
    @Override
    public Payment mapToPayment(PaymentRequestDTO paymentRequestDTO) {
        return new Payment(
                paymentRequestDTO.getCurrency(),
                paymentRequestDTO.getAmount(),
                paymentRequestDTO.getTime(),
                paymentRequestDTO.getMessage(),
                paymentRequestDTO.getBill());
    }

    @Override
    public PaymentResponseDTO mapToPaymentResponseDTO(Payment payment) {
        return new PaymentResponseDTO(
                payment.getId(),
                payment.getCurrency(),
                payment.getAmount(),
                payment.getTime(),
                payment.getMessage()
                );
    }

    @Override
    public FinancialTransactionResponseDTO mapToFinancialTransactionResponseDTO(Payment payment) {
        return new FinancialTransactionResponseDTO(
                payment.getId(),
                payment.getAmount(),
                payment.getTime(),
                payment.getMessage(),
                payment.getCurrency()
        );
    }
}
