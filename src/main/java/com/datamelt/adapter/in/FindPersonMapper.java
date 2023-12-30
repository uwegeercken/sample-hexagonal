package com.datamelt.adapter.in;

import com.datamelt.adapter.model.FindPersonDto;
import com.datamelt.domain.model.Person;

public class FindPersonMapper
{
    public static FindPersonDto getFindPersonDto(Person person)
    {
        return new FindPersonDto(person.getLastname(), person.getFirstname(), person.getAge());
    }

    public static Person getPerson(FindPersonDto findPersonDto)
    {
        return new Person(findPersonDto.lastname(), findPersonDto.firstname());
    }
}
