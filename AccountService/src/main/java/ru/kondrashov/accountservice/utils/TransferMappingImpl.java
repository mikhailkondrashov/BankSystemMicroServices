package ru.kondrashov.accountservice.utils;

import org.springframework.stereotype.Component;
import ru.kondrashov.accountservice.controllers.dto.FinancialTransactionResponseDTO;
import ru.kondrashov.accountservice.controllers.dto.TransferRequestDTO;
import ru.kondrashov.accountservice.controllers.dto.TransferResponseDTO;
import ru.kondrashov.accountservice.entities.Transfer;
import ru.kondrashov.accountservice.utils.interfaces.TransferMapping;

@Component
public class TransferMappingImpl implements TransferMapping {


    @Override
    public Transfer mapToTransfer(TransferRequestDTO transferRequestDTO) {
        return new Transfer(
                transferRequestDTO.getCurrency(),
                transferRequestDTO.getAmount(),
                transferRequestDTO.getTime(),
                transferRequestDTO.getMessage(),
                transferRequestDTO.getSourceBill(),
                transferRequestDTO.getBeneficiaryBill()
        );
    }

    @Override
    public TransferResponseDTO mapToTransferResponseDTO(Transfer transfer){
        return new TransferResponseDTO(
                transfer.getId(),
                transfer.getCurrency(),
                transfer.getAmount(),
                transfer.getTime(),
                transfer.getMessage(),
                transfer.getSourceBill(),
                transfer.getBeneficiaryBill()
        );
    }

    @Override
    public FinancialTransactionResponseDTO mapToFinancialTransactionResponseDTO(Transfer transfer){
        return new FinancialTransactionResponseDTO(
          transfer.getId(),
          transfer.getCurrency(),
          transfer.getAmount(),
          transfer.getTime(),
          transfer.getMessage()

        );
    }
}
