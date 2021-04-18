package ru.kondrashov.accountservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kondrashov.accountservice.entities.Transfer;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {

    Collection<Transfer> getTransfersBySourceBill_Id(UUID sourceId);

    Collection<Transfer> getTransfersByBeneficiaryBill_Id(UUID sourceId);
}
