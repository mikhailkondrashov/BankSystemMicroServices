package ru.kondrashov.accountservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kondrashov.accountservice.entities.Adjustment;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface AdjustmentsRepository extends JpaRepository<Adjustment, UUID> {

    Collection<Adjustment> getAdjustmentsByBill_Id(UUID id);

}
