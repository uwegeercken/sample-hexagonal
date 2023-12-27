package com.datamelt.port.in.person;

import com.datamelt.domain.model.Person;

public interface FindPersonUseCase
{
    Person findByName(String lastname, String firstname);
}
