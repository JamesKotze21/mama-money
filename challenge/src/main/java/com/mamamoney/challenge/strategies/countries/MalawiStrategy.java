package com.mamamoney.challenge.strategies.countries;

import com.mamamoney.challenge.models.domain.enums.CurrencyCode;
import org.springframework.stereotype.Component;

@Component
public class MalawiStrategy implements CountryStrategy {

    private final String MALAWI = "Malawi";
    private final Float MALAWI_RATE = 42.50f;

    @Override
    public String getFriendlyName() {
        return MALAWI;
    }

    @Override
    public float convertCurrency(float amount) {
        return amount * MALAWI_RATE;
    }

    @Override
    public CurrencyCode getCurrencyCode() {
        return CurrencyCode.MWK;
    }
}