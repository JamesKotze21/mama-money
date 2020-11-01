package com.mamamoney.challenge.models.domain;

import com.mamamoney.challenge.models.domain.enums.CountryCode;

public class FinalRequest {

    private CountryCode countryCode;
    private float amount;

    public FinalRequest() {
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "FinalRequest{" +
                "countryCode=" + countryCode +
                ", amount=" + amount +
                '}';
    }
}
