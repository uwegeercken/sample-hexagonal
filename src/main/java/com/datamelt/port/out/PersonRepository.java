package com.datamelt.port.out;

import com.datamelt.domain.model.Person;

import java.util.Optional;

public interface PersonRepository
{
    Person save(Person person);
    Optional<Person> deleteByName(String lastname, String firstname);
    Optional<Person> findByName(String lastname, String firstname);
}
