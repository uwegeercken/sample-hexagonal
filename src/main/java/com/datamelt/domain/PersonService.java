package com.datamelt.domain;

import com.datamelt.domain.model.Person;
import com.datamelt.port.in.person.PersonUseCase;
import com.datamelt.port.out.PersonRepository;

import java.util.Optional;

public class PersonService implements PersonUseCase
{
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }
    @Override
    public Optional<Person> findByName(String lastname, String firstname)
    {
        return personRepository.findByName(lastname, firstname);
    }

    @Override
    public int save(Person person)
    {
        return personRepository.save(person);
    }

    @Override
    public void delete(Person person)
    {
        personRepository.delete(person);
    }
}
