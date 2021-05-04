package ru.kondrashov.accountservice.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kondrashov.accountservice.entities.Adjustment;
import ru.kondrashov.accountservice.repositories.AdjustmentsRepository;
import ru.kondrashov.accountservice.services.interfaces.AdjustmentsService;

import java.util.Collection;
import java.util.UUID;

@Service
public class AdjustmentsServiceImpl implements AdjustmentsService {

    private final AdjustmentsRepository adjustmentsRepository;

    public AdjustmentsServiceImpl(AdjustmentsRepository adjustmentsRepository) {
        this.adjustmentsRepository = adjustmentsRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Adjustment adjustment) {
        adjustmentsRepository.save(adjustment);
    }

    @Override
    public Collection<Adjustment> getAdjustmentsByBillId(UUID billId) {
        return adjustmentsRepository.getAdjustmentsByBill_Id(billId);
    }
}
