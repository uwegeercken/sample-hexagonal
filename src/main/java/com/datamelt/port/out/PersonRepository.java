package com.datamelt.port.out;

import com.datamelt.domain.model.Person;

public interface PersonRepository
{
    void save(Person person);
    Person findByName(String lastname, String firstname);
}
