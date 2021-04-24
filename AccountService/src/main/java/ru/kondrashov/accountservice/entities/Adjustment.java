package ru.kondrashov.accountservice.entities;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Data
@Table(name = "adjustments", schema = "public")
public class Adjustment extends FinancialTransaction{

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "bill_id")
    @NotNull(message = "Bill should not be null")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Bill bill;

    public Adjustment(Currency currency, BigDecimal amount, LocalDateTime time, String message, Bill bill){
        super(currency,amount,time,message);
        this.bill = bill;
    }


    public Adjustment() {

    }
}
