package ru.kondrashov.commonservice.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillResponseDTO {

    @NotNull
    private Currency currency;

    private BigDecimal amount = BigDecimal.ZERO;

    private Boolean isOverdraft;

    private AccountRequestDTO account;

    public Boolean isOverdraft(){
        return isOverdraft;
    }

    public void isOverdraft(String isOverdraft){
        this.isOverdraft= Boolean.getBoolean(isOverdraft);
    }
}
