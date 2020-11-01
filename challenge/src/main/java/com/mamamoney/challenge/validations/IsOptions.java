package com.mamamoney.challenge.validations;

import com.mamamoney.challenge.exceptions.InvalidUserRequestException;

import java.util.List;

public class IsOptions implements Rule {

    List<Integer> options;

    public IsOptions(List<Integer> options) {
        this.options = options;
    }

    @Override
    public void validate(String userInput) throws InvalidUserRequestException {
        if (!options.contains(Integer.parseInt(userInput))) {
            throw new InvalidUserRequestException("Select a valid option");
        }
    }
}
