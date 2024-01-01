package com.datamelt.domain;

import com.datamelt.application.SampleApplication;
import com.datamelt.domain.model.Person;
import com.datamelt.port.in.person.FindPersonUseCase;
import com.datamelt.port.out.PersonRepository;

import java.util.Optional;

public class FindPersonService implements FindPersonUseCase
{
    private final PersonRepository personRepository;

    public FindPersonService(PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }
    @Override
    public Optional<Person> findByName(String lastname, String firstname)
    {
        return personRepository.findByName(lastname, firstname);
    }
}
