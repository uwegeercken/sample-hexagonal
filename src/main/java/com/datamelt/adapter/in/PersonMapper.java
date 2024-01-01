package com.datamelt.adapter.in;

import com.datamelt.adapter.model.PersonDto;
import com.datamelt.domain.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonMapper
{
    private final ObjectMapper objectMapper;
    public PersonMapper(ObjectMapper objectMapper)
    {
        this.objectMapper = objectMapper;
    }

    public String getPersonDto(Person person, String status)
    {
        StringWriter writer = new StringWriter();
        try
        {
            objectMapper.writeValue(writer, new PersonDto(person.getId(), person.getLastname(), person.getFirstname(), person.getAge(),status));
        }
        catch (Exception ex)
        {
            writer.write("{}");
        }
        return writer.toString();
    }

    public static Person getPerson(String lastname, String firstname, String dateOfBirth)
    {
        return new Person(lastname, firstname, LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
