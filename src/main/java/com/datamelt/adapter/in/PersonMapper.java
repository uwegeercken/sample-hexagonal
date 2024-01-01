package com.datamelt.adapter.in;

import com.datamelt.adapter.model.PersonDto;
import com.datamelt.domain.model.Person;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonMapper
{
    public PersonDto getPersonDto(Person person, Status status)
    {
        return new PersonDto(person.getId(), person.getLastname(), person.getFirstname(), person.getAge(), status);
    }

    public Person getPerson(String lastname, String firstname, String dateOfBirth)
    {
        return new Person(lastname, firstname, LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
