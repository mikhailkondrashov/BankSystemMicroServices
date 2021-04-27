package ru.kondrashov.accountservice.utils;

import org.springframework.stereotype.Component;
import ru.kondrashov.accountservice.controllers.dto.AdjustmentRequestDTO;
import ru.kondrashov.accountservice.controllers.dto.AdjustmentResponseDTO;
import ru.kondrashov.accountservice.controllers.dto.FinancialTransactionResponseDTO;
import ru.kondrashov.accountservice.entities.Adjustment;
import ru.kondrashov.accountservice.utils.interfacies.AdjustmentMapping;

@Component
public class AdjustmentMappingImpl implements AdjustmentMapping {
    @Override
    public Adjustment mapToAdjustment(AdjustmentRequestDTO adjustmentRequestDTO) {
        return new Adjustment(
                adjustmentRequestDTO.getCurrency(),
                adjustmentRequestDTO.getAmount(),
                adjustmentRequestDTO.getTime(),
                adjustmentRequestDTO.getMessage(),
                adjustmentRequestDTO.getBill()
        );
    }

    @Override
    public AdjustmentResponseDTO mapToAdjustmentResponseDTO(Adjustment adjustment) {
        return new AdjustmentResponseDTO(
                adjustment.getId(),
                adjustment.getCurrency(),
                adjustment.getAmount(),
                adjustment.getTime(),
                adjustment.getMessage()

        );
    }

    @Override
    public FinancialTransactionResponseDTO mapToFinancialTransactionResponseDTO(Adjustment adjustment) {
        return new FinancialTransactionResponseDTO(
                adjustment.getId(),
                adjustment.getCurrency(),
                adjustment.getAmount(),
                adjustment.getTime(),
                adjustment.getMessage()
        );
    }
}
