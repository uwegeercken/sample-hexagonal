package com.datamelt.port.in.person;

import com.datamelt.domain.model.Person;

import java.time.LocalDate;
import java.util.Optional;

public interface FindPersonUseCase
{
    Optional<Person> findByName(String lastname, String firstname);
    int save(Person person);
}
