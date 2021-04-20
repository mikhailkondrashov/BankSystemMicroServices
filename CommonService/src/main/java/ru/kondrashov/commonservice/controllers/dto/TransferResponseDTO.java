package ru.kondrashov.commonservice.controllers.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
public class TransferResponseDTO {

    @NotNull(message = "Message should not null")
    private Currency currency;

    @Min(message = "Amount should be more than zero", value = 0)
    private BigDecimal amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime time = LocalDateTime.now();

    @NotEmpty(message = "Message should not empty")
    private String message;

    @NotNull(message = "Bill should not be null")
    private BillRequestDTO sourceBill;

    @NotNull(message = "Bill should not be null")
    private BillRequestDTO beneficiaryBill;
}
