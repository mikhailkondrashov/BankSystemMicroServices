package ru.kondrashov.accountservice.utils;

import org.junit.jupiter.api.Test;
import ru.kondrashov.accountservice.controllers.dto.FinancialTransactionResponseDTO;
import ru.kondrashov.accountservice.controllers.dto.TransferRequestDTO;
import ru.kondrashov.accountservice.controllers.dto.TransferResponseDTO;
import ru.kondrashov.accountservice.entities.Bill;
import ru.kondrashov.accountservice.entities.Transfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class TransferMappingImplTest {

    TransferMappingImpl transferMapping = new TransferMappingImpl();

    @Test
    void mapToTransfer() {

        Bill sourceBill = new Bill();
        Bill beneficiaryBill = new Bill();

        TransferRequestDTO transferRequestDTO = new TransferRequestDTO(
                Currency.getInstance("USD"),
                BigDecimal.TEN,
                LocalDateTime.now(),
                "Transfer",
                sourceBill,
                beneficiaryBill
        );

        Transfer transfer = transferMapping.mapToTransfer(transferRequestDTO);

        assertEquals(transfer.getCurrency(),        transferRequestDTO.getCurrency());
        assertEquals(transfer.getAmount(),          transferRequestDTO.getAmount());
        assertEquals(transfer.getMessage(),         transferRequestDTO.getMessage());
        assertEquals(transfer.getSourceBill(),      transferRequestDTO.getSourceBill());
        assertEquals(transfer.getBeneficiaryBill(), transferRequestDTO.getBeneficiaryBill());
        assertEquals(transfer.getTime(),            transferRequestDTO.getTime());
    }

    @Test
    void mapToTransferResponseDTO() {
        Bill sourceBill = new Bill();
        Bill beneficiaryBill = new Bill();

        Transfer transfer = new Transfer(
                Currency.getInstance("USD"),
                BigDecimal.TEN,
                LocalDateTime.now(),
                "Transfer",
                sourceBill,
                beneficiaryBill
        );

        TransferResponseDTO transferResponseDTO = transferMapping.mapToTransferResponseDTO(transfer);

        assertEquals(transfer.getCurrency(),        transferResponseDTO.getCurrency());
        assertEquals(transfer.getAmount(),          transferResponseDTO.getAmount());
        assertEquals(transfer.getMessage(),         transferResponseDTO.getMessage());
        assertEquals(transfer.getSourceBill(),      transferResponseDTO.getSourceBill());
        assertEquals(transfer.getBeneficiaryBill(), transferResponseDTO.getBeneficiaryBill());
        assertEquals(transfer.getTime(),            transferResponseDTO.getTime());
    }

    @Test
    void mapToFinancialTransactionResponseDTO() {

        Bill sourceBill = new Bill();
        Bill beneficiaryBill = new Bill();

        Transfer transfer = new Transfer(
                Currency.getInstance("USD"),
                BigDecimal.TEN,
                LocalDateTime.now(),
                "Transfer",
                sourceBill,
                beneficiaryBill
        );

        FinancialTransactionResponseDTO financialTransactionResponseDTO = transferMapping.mapToFinancialTransactionResponseDTO(transfer);

        assertEquals(transfer.getCurrency(),        financialTransactionResponseDTO.getCurrency());
        assertEquals(transfer.getAmount(),          financialTransactionResponseDTO.getAmount());
        assertEquals(transfer.getMessage(),         financialTransactionResponseDTO.getMessage());
        assertEquals(transfer.getTime(),            financialTransactionResponseDTO.getTime());
    }
}