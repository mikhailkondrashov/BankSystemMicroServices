package ru.kondrashov.accountservice.services;

import org.javamoney.moneta.spi.MoneyUtils;
import org.springframework.stereotype.Service;
import ru.kondrashov.accountservice.services.interfacies.FinancialCurrencyExchange;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Currency;

@Service
public class JavaMonetaApiCurrencyExchange implements FinancialCurrencyExchange {

    @Override
    public BigDecimal exchange(Currency originalCurrency, Currency targetCurrency, BigDecimal amount) {

        CurrencyUnit targetCurrencyUnit = Monetary.getCurrency(targetCurrency.getCurrencyCode());
        CurrencyUnit originalCurrencyUnit = Monetary.getCurrency(originalCurrency.getCurrencyCode());
        MonetaryAmount originalMonetaryAmount = Monetary.getDefaultAmountFactory().setCurrency(originalCurrencyUnit).setNumber(amount).create();

        CurrencyConversion toTargetCurrency = MonetaryConversions.getConversion(targetCurrencyUnit);

        return MoneyUtils.getBigDecimal(originalMonetaryAmount.with(toTargetCurrency).getNumber()).round(new MathContext(2, RoundingMode.HALF_EVEN));
    }
}
