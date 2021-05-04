package ru.kondrashov.accountservice.utils.interfaces;

import ru.kondrashov.accountservice.controllers.dto.FinancialTransactionResponseDTO;
import ru.kondrashov.accountservice.controllers.dto.TransferRequestDTO;
import ru.kondrashov.accountservice.controllers.dto.TransferResponseDTO;
import ru.kondrashov.accountservice.entities.Transfer;

public interface TransferMapping {
    Transfer mapToTransfer(TransferRequestDTO transferRequestDTO);

    TransferResponseDTO mapToTransferResponseDTO(Transfer transfer);

    FinancialTransactionResponseDTO mapToFinancialTransactionResponseDTO(Transfer transfer);
}
