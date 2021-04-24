package ru.kondrashov.accountservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "payments", schema = "public")
public class Payment extends FinancialTransaction {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "bill_id")
    @NotNull(message = "Bill should not be null")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Bill bill;

    public Payment(Currency currency, BigDecimal amount, LocalDateTime time, String message, Bill bill){
        super(currency,amount,time,message);
        this.bill = bill;
    }

}
