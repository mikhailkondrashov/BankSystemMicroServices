package ru.kondrashov.accountservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bills", schema = "public")
public class Bill {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private Currency currency;

    private BigDecimal amount = BigDecimal.ZERO;

    @NotNull
    private Boolean isOverdraft;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    @NotNull
    private Account account;

    public Bill(Currency currency, BigDecimal amount, boolean isOverdraft, @NotNull Account account) {
        this.currency = currency;
        this.amount = amount;
        this.isOverdraft = isOverdraft;
        this.account = account;
    }
}
