package com.datamelt.port.in.person;

import com.datamelt.domain.model.Person;

import java.util.Optional;

public interface PersonUseCase
{
    Optional<Person> findByName(String lastname, String firstname);
    Person save(Person person);
    Optional<Person> deleteByName(String lastname, String firstname);
}
