package com.safetynet.alerts.services;

import java.text.ParseException;
import java.util.Date;

import com.safetynet.alerts.model.Person;

public interface ServiceUtil {
    
    Boolean isChild(Person person) throws ParseException;

    Boolean isChild(int age);

    int calculateAge(Date date);
    
}
