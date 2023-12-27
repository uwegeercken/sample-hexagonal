package com.datamelt.adapter.out;

import com.datamelt.domain.model.Person;
import com.datamelt.port.out.PersonRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPersonRepository implements PersonRepository
{
    private final Map<Long, Person> persons = new ConcurrentHashMap<>();
    @Override
    public void save(Person person)
    {
        persons.put(1, person);
    }

    @Override
    public Person findByName(String lastname, String firstname)
    {
        return null;
    }
}
