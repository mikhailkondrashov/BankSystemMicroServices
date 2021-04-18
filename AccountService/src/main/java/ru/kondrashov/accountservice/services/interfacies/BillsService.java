package ru.kondrashov.accountservice.services.interfacies;

import org.springframework.transaction.annotation.Transactional;
import ru.kondrashov.accountservice.controllers.dto.BillResponseDTO;
import ru.kondrashov.accountservice.entities.Adjustment;
import ru.kondrashov.accountservice.entities.Bill;
import ru.kondrashov.accountservice.entities.Payment;
import ru.kondrashov.accountservice.entities.Transfer;
import ru.kondrashov.accountservice.repositories.BillsRepository;

import java.util.Collection;
import java.util.UUID;

public interface BillsService {

    Collection<Bill> getBills();
    Bill getBill(UUID id);
    Collection<Bill> getBillsByAccountId(UUID accountID);
    void save(Bill bill);
    void delete(UUID id);
    void update (UUID id, Bill bill);
    void commitAdjustment(UUID billId, Adjustment adjustment);
    void commitPayment(UUID billId, Payment payment);
    void commitTransfer(UUID sourceBillId, UUID beneficiaryBillId, Transfer transfer);
    Collection<Payment> getPaymentsByBillId(UUID billId);
    Collection<Adjustment> getAdjustmentsByBillId(UUID billId);
    Collection<Transfer> getTransfersBySourceBillId(UUID billId);
    Collection<Transfer> getTransfersByBeneficiaryBillId(UUID billId);
}
