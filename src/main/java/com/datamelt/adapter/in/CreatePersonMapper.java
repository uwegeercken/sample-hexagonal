package com.datamelt.adapter.in;

import com.datamelt.adapter.model.CreatePersonDto;
import com.datamelt.adapter.model.FindPersonDto;
import com.datamelt.domain.model.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreatePersonMapper
{
    public static CreatePersonDto getCreatePersonDto(Person person)
    {
        return new CreatePersonDto(person.getLastname(), person.getFirstname(), person.getDateOfBirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public static Person getPerson(CreatePersonDto createPersonDto)
    {
        return new Person(createPersonDto.lastname(), createPersonDto.firstname(), LocalDate.parse(createPersonDto.dateOfBirth(),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
