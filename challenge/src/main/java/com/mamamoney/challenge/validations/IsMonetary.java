package com.mamamoney.challenge.validations;

import com.mamamoney.challenge.exceptions.InvalidUserRequestException;

public class IsMonetary implements Rule {

    @Override
    public void validate(String str) throws InvalidUserRequestException {
        try {
            Float.parseFloat(str);
        } catch (NumberFormatException ex) {
            throw new InvalidUserRequestException("Please insert a valid number");
        }
    }
}
