package com.datamelt.port.in.person;

import com.datamelt.domain.model.Person;

import java.util.Optional;

public interface PersonUseCase
{
    Optional<Person> findByName(String lastname, String firstname);
    int save(Person person);
    void delete(Person person);
}
