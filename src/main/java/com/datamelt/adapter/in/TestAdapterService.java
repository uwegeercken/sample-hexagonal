package com.datamelt.adapter.in;

import com.datamelt.adapter.model.CreatePersonDto;
import com.datamelt.adapter.model.FindPersonDto;
import com.datamelt.adapter.model.FindPersonRequest;
import com.datamelt.domain.model.Person;
import com.datamelt.port.in.person.PersonUseCase;

import java.util.Optional;

public class TestAdapterService
{
    private final PersonUseCase personUseCase;

    public TestAdapterService(PersonUseCase personUseCase)
    {
        this.personUseCase = personUseCase;
    }

    public Optional<FindPersonDto> findByName(FindPersonRequest findPersonRequest)
    {
        Optional<Person> person = personUseCase.findByName(findPersonRequest.lastname(), findPersonRequest.firstname());
        return person.map(FindPersonMapper::getFindPersonDto);
    }

    public int createPerson(CreatePersonDto createPersonDto)
    {
        return personUseCase.save(CreatePersonMapper.getPerson(createPersonDto));
    }

    public void deletePerson(FindPersonDto findPersonDto)
    {
        personUseCase.delete(FindPersonMapper.getPerson(findPersonDto));
    }
}
