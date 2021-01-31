package net.safety.safetalerts.service;

import net.safety.safetalerts.model.Persons;
import org.json.simple.parser.ParseException;


public interface ServiceUtil {

    Boolean isChild(Persons person) throws ParseException;

    Boolean isChild(int age);

    int calculateAge(String date);
}
