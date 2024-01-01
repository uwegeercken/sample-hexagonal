package com.datamelt.adapter.in;

import com.datamelt.adapter.model.CreatePersonDto;
import com.datamelt.adapter.model.FindPersonDto;
import com.datamelt.adapter.model.FindPersonRequest;
import com.datamelt.domain.model.Person;
import com.datamelt.port.in.person.FindPersonUseCase;

import java.util.Optional;

public class TestAdapterService
{
    private final FindPersonUseCase findPersonUseCase;

    public TestAdapterService(FindPersonUseCase findPersonUseCase)
    {
        this.findPersonUseCase = findPersonUseCase;
    }

    public Optional<FindPersonDto> findByName(FindPersonRequest findPersonRequest)
    {
        Optional<Person> person = findPersonUseCase.findByName(findPersonRequest.lastname(), findPersonRequest.firstname());
        return person.map(FindPersonMapper::getFindPersonDto);
    }

    public int createPerson(CreatePersonDto createPersonDto)
    {
        return findPersonUseCase.save(CreatePersonMapper.getPerson(createPersonDto));
    }
}
