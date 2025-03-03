package com.br.InveMedi.inveMedi.services.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.w3c.dom.Entity;

@ResponseStatus( value = HttpStatus.NOT_FOUND)

public class ObjectNotFoundException extends EntityNotFoundException {

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
