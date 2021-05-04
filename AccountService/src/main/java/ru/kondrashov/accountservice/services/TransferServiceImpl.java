package ru.kondrashov.accountservice.services;

import org.springframework.stereotype.Service;
import ru.kondrashov.accountservice.entities.Transfer;
import ru.kondrashov.accountservice.repositories.TransferRepository;
import ru.kondrashov.accountservice.services.interfaces.TransferService;

import java.util.Collection;
import java.util.UUID;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public void save(Transfer transfer) {
        transferRepository.save(transfer);
    }

    @Override
    public Collection<Transfer> getTransfersBySourceBill_Id(UUID sourceId) {
        return transferRepository.getTransfersBySourceBill_Id(sourceId);
    }

    @Override
    public Collection<Transfer> getTransfersByBeneficiaryBill_Id(UUID sourceId) {
        return transferRepository.getTransfersByBeneficiaryBill_Id(sourceId);
    }
}
