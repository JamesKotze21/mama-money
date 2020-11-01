package com.mamamoney.challenge.validations;

import com.mamamoney.challenge.exceptions.InvalidUserRequestException;

import java.util.List;
import java.util.Optional;

public interface Rule {
    void validate(String userInput) throws InvalidUserRequestException;
}
