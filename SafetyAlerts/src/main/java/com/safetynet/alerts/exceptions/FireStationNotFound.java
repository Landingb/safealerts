package com.safetynet.alerts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FireStationNotFound extends RuntimeException {
    public FireStationNotFound(String s) {
        super("Fire station not found"+s);
    }
}
