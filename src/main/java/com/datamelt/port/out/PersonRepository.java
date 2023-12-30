package com.datamelt.port.out;

import com.datamelt.domain.model.Person;

import java.util.Optional;

public interface PersonRepository
{
    int save(Person person);
    Optional<Person> findByName(String lastname, String firstname);
}
