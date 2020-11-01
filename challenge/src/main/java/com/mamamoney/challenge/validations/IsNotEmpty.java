package com.mamamoney.challenge.validations;

import com.mamamoney.challenge.exceptions.InvalidUserRequestException;
import org.springframework.util.StringUtils;

public class IsNotEmpty implements Rule {

    @Override
    public void validate(String str) throws InvalidUserRequestException {
        // StringUtils.isEmpty also accounts for null values
        if (StringUtils.isEmpty(str)) {
            throw new InvalidUserRequestException("Please insert a valid number");
        }
    }
}