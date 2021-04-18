package ru.kondrashov.accountservice.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kondrashov.accountservice.entities.Account;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillRequestDTO {

    @NotNull
    private Currency currency;

    private BigDecimal amount ;
    @NotNull
    private Boolean isOverdraft;

    @NotNull
    private Account account;
}
