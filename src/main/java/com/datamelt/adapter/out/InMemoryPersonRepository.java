package com.datamelt.adapter.out;

import com.datamelt.domain.model.Person;
import com.datamelt.port.out.PersonRepository;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPersonRepository implements PersonRepository
{
    private final Map<Integer, Person> persons = new ConcurrentHashMap<>();

    public InMemoryPersonRepository()
    {
        loadPersons();
    }

    public void loadPersons()
    {
        Person p1 = new Person("Lipton", "Larry", LocalDate.of(1965,4,17));
        save(p1);

        Person p2 = new Person("Kube", "Mary", LocalDate.of(1997,1,2));
        save(p2);

    }

    @Override
    public int save(Person person)
    {
        int personId = findIdByName(person.getLastname(), person.getFirstname()).orElseGet(persons::size);
        persons.put(personId, person);
        return personId;
    }

    private Optional<Integer> findIdByName(String lastname, String firstname)
    {
        return persons.entrySet()
                .stream()
                .filter(entry -> entry.getValue().hasEqualName(lastname, firstname))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    @Override
    public Optional<Person> findByName(String lastname, String firstname)
    {
        return persons.values()
                .stream()
                .filter(person -> person.hasEqualName(lastname, firstname))
                .findFirst();
    }
}
