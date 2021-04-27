package ru.kondrashov.accountservice.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ru.kondrashov.accountservice.entities.Bill;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdjustmentRequestDTO {

    @NotNull(message = "Message should not null")
    private Currency currency;

    @Min(message = "Amount should be more than zero", value = 0)
    private BigDecimal amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime time = LocalDateTime.now();

    @NotEmpty(message = "Message should not empty")
    private String message;

    @NotNull(message = "Bill should not be null")
    private Bill bill;
}
