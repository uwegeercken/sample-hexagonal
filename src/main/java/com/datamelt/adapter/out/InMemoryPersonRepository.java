package com.datamelt.adapter.out;

import com.datamelt.domain.model.Person;
import com.datamelt.port.out.PersonRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryPersonRepository implements PersonRepository
{
    private final Map<Integer, Person> persons = new HashMap<>();

    private List<Person> samplePersons = List.of(
            new Person("Lipton", "Larry", LocalDate.of(1965,4,17)),
            new Person("Kube", "Mary", LocalDate.of(1997,1,2)),
            new Person("Mason", "Bill", LocalDate.of(1977,8,27)),
            new Person("Carlson", "Carl", LocalDate.of(1981,5,21)),
            new Person("Straight", "Josephine", LocalDate.of(1971,11,2)),
            new Person("Gaga", "Judith", LocalDate.of(2005, 6,16))
    );

    public InMemoryPersonRepository()
    {
        loadPersons();
    }

    private void loadPersons()
    {
        samplePersons.forEach(this::save);
    }

    @Override
    public Person save(Person person)
    {
        int personId = findIdByName(person.getLastname(), person.getFirstname()).orElseGet(persons::size);
        person.setId(personId);
        persons.put(personId, person);
        return person;
    }

    @Override
    public Optional<Person> deleteByName(String lastname, String firstname)
    {
        Optional<Person> deletePerson = persons.values()
                .stream()
                .filter(selectedPerson -> selectedPerson.hasEqualName(lastname, firstname))
                .findFirst();

        deletePerson.ifPresent(value -> persons.remove(value.getId()));
        return deletePerson;
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
