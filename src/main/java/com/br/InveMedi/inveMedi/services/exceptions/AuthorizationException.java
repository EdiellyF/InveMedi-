package com.br.InveMedi.inveMedi.services.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND)
public class AuthorizationException extends AccessDeniedException {



    public AuthorizationException(String msg) {
        super(msg);
    }
}
