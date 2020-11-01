package com.mamamoney.challenge.strategies.countries;

import com.mamamoney.challenge.models.domain.enums.CurrencyCode;

public interface CountryStrategy {

    public String getFriendlyName();

    public float convertCurrency(float amount);

    public CurrencyCode getCurrencyCode();
}
