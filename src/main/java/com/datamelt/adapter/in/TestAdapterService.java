package com.datamelt.adapter.in;

import com.datamelt.adapter.model.PersonDto;
import com.datamelt.adapter.model.FindPersonRequest;
import com.datamelt.domain.model.Person;
import com.datamelt.port.in.person.PersonUseCase;

import java.util.Optional;

public class TestAdapterService
{
    private final PersonUseCase personUseCase;
    private final PersonMapper personMapper;

    public TestAdapterService(PersonUseCase personUseCase, PersonMapper personMapper)
    {
        this.personUseCase = personUseCase;
        this.personMapper = personMapper;
    }

    public String findByName(FindPersonRequest findPersonRequest)
    {

        Optional<Person> person = personUseCase.findByName(findPersonRequest.lastname(), findPersonRequest.firstname());
        if(person.isPresent())
        {
            return personMapper.getPersonDto(person.get(),"found");
        }
        else
        {
            return personMapper.getPersonDto(new Person(findPersonRequest.lastname(), findPersonRequest.firstname()),"not found");
        }
    }

    public String createPerson(String lastname, String firstname, String dateOfBirth)
    {
        Person person = personUseCase.save(PersonMapper.getPerson(lastname, firstname, dateOfBirth));
        return personMapper.getPersonDto(person, "created");
    }

    public String deletePersonByName(String lastname, String firstname)
    {
        Optional<Person> person = personUseCase.deleteByName(lastname, firstname);
        if(person.isPresent())
        {
            return personMapper.getPersonDto(person.get(), "deleted");
        }
        else
        {
            return personMapper.getPersonDto(new Person(lastname, firstname),"not found");
        }
    }
}
