package com.safetynet.alerts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MedicalRecordNotFound extends RuntimeException {
    public MedicalRecordNotFound(String s) {
        super("Medical record not found " + s);
    }
}
