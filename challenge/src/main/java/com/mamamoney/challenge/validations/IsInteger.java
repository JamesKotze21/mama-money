package com.mamamoney.challenge.validations;

import com.mamamoney.challenge.exceptions.InvalidUserRequestException;

public class IsInteger implements Rule {

    @Override
    public void validate(String str) throws InvalidUserRequestException {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            throw new InvalidUserRequestException("Please insert a valid number");
        }
    }
}
