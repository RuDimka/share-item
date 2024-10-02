package com.project.share_item.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ObjectNotFoundExceptions extends ResponseStatusException {

    public ObjectNotFoundExceptions(HttpStatusCode status) {
        super(status);
    }
}
