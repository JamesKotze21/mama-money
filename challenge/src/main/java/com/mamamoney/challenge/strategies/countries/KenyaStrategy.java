package com.mamamoney.challenge.strategies.countries;

import com.mamamoney.challenge.models.domain.enums.CurrencyCode;
import org.springframework.stereotype.Component;

@Component
public class KenyaStrategy implements CountryStrategy {

    private final String KENYA = "Kenya";
    private final Float KENYA_RATE = 6.10f;

    @Override
    public String getFriendlyName() {
        return KENYA;
    }

    @Override
    public float convertCurrency(float amount) {
        return amount * KENYA_RATE;
    }

    @Override
    public CurrencyCode getCurrencyCode() {
        return CurrencyCode.KES;
    }
}
