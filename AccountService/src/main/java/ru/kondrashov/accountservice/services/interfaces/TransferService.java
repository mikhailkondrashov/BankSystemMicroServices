package ru.kondrashov.accountservice.services.interfaces;

import ru.kondrashov.accountservice.entities.Transfer;

import java.util.Collection;
import java.util.UUID;

public interface TransferService {

    void save(Transfer transfer);

    Collection<Transfer> getTransfersBySourceBill_Id(UUID sourceId);

    Collection<Transfer> getTransfersByBeneficiaryBill_Id(UUID sourceId);

}
