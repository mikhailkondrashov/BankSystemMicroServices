package ru.kondrashov.accountservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Data
@NoArgsConstructor
@Table(name = "transfers",schema = "public")
public class Transfer extends FinancialTransaction{

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "sourceBill_id")
    @NotNull(message = "Bill should not be null")
    private Bill sourceBill;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "beneficiaryBill_id")
    @NotNull(message = "Bill should not be null")
    private Bill beneficiaryBill;

    public Transfer(Currency currency, BigDecimal amount, LocalDateTime time, String message, Bill sourceBill, Bill beneficiaryBill){
        super(currency,amount,time,message);
        this.sourceBill = sourceBill;
        this.beneficiaryBill = beneficiaryBill;
    }
}
