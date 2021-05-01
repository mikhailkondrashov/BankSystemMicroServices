package ru.kondrashov.accountservice.services;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
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
@RequiredArgsConstructor
public class JavaMonetaApiCurrencyExchange implements FinancialCurrencyExchange {

    private final Logger logger;

    @Override
    public BigDecimal exchange(Currency originalCurrency, Currency targetCurrency, BigDecimal amount) {

        CurrencyUnit targetCurrencyUnit = Monetary.getCurrency(targetCurrency.getCurrencyCode());
        CurrencyUnit originalCurrencyUnit = Monetary.getCurrency(originalCurrency.getCurrencyCode());
        MonetaryAmount originalMonetaryAmount = Monetary.getDefaultAmountFactory().setCurrency(originalCurrencyUnit).setNumber(amount).create();

        CurrencyConversion toTargetCurrency = MonetaryConversions.getConversion(targetCurrencyUnit);

        BigDecimal result = MoneyUtils.getBigDecimal(originalMonetaryAmount.with(toTargetCurrency).getNumber()).round(new MathContext(2, RoundingMode.HALF_EVEN));
        logger.info("Currency conversion from"+originalCurrency.getCurrencyCode()+" to "+ targetCurrency.getCurrencyCode()+" in the amount of "+amount+" "+originalCurrency.getCurrencyCode()+" is performed");
        return result;
    }
}
