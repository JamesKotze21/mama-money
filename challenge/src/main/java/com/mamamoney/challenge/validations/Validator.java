package com.mamamoney.challenge.validations;

import com.mamamoney.challenge.exceptions.InvalidUserRequestException;
import com.mamamoney.challenge.models.ClientRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Validator {
    public void validate(ClientRequest request, List<Rule> rules) throws InvalidUserRequestException {
        for (Rule rule : rules) {
            rule.validate(request.getUserEntry());
        }
    }
}
