package com.mamamoney.challenge.controller;

import com.mamamoney.challenge.exceptions.InvalidUserRequestException;
import com.mamamoney.challenge.models.ClientRequest;
import com.mamamoney.challenge.models.ClientResponse;
import com.mamamoney.challenge.models.domain.*;
import com.mamamoney.challenge.models.domain.enums.CountryCode;
import com.mamamoney.challenge.models.domain.enums.SessionState;
import com.mamamoney.challenge.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mama-money")
public class MamaController {

    @Autowired
    SessionService sessionService;

    @RequestMapping(path = "/ussd", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<ClientResponse> ussd (@RequestBody ClientRequest request) {
        try {
            return sessionService.receiveUssdRequest(request);
        } catch (InvalidUserRequestException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @RequestMapping(path = "/ussd-mongo", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<ClientResponse> ussdMongo (@RequestBody ClientRequest request) {
        try {
            return sessionService.receiveUssdRequestMongo(request);
        } catch (InvalidUserRequestException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
    }

}
