package ru.kondrashov.accountservice.utils.interfaces;

import ru.kondrashov.accountservice.controllers.dto.AdjustmentRequestDTO;
import ru.kondrashov.accountservice.controllers.dto.AdjustmentResponseDTO;
import ru.kondrashov.accountservice.controllers.dto.FinancialTransactionResponseDTO;
import ru.kondrashov.accountservice.entities.Adjustment;

public interface AdjustmentMapping {

    Adjustment mapToAdjustment(AdjustmentRequestDTO adjustmentRequestDTO);

    AdjustmentResponseDTO mapToAdjustmentResponseDTO(Adjustment adjustment);

    FinancialTransactionResponseDTO mapToFinancialTransactionResponseDTO(Adjustment adjustment);
}
