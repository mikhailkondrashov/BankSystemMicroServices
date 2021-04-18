package ru.kondrashov.accountservice.services.interfacies;

import java.math.BigDecimal;
import java.util.Currency;

public interface FinancialCurrencyExchange {


    BigDecimal exchange(Currency originalCurrency, Currency targetCurrency, BigDecimal amount);
}
