package ru.kondrashov.accountservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Account account;

    public Bill(@NotNull Currency currency, BigDecimal amount, @NotNull boolean isOverdraft, @NotNull Account account) {
        this.currency = currency;
        this.amount = amount;
        this.isOverdraft = isOverdraft;
        this.account = account;
    }
}
