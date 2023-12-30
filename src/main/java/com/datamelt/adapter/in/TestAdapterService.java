package com.datamelt.adapter.in;

import com.datamelt.adapter.model.FindPersonDto;
import com.datamelt.adapter.model.TestRequest;
import com.datamelt.application.SampleApplication;
import com.datamelt.domain.model.Person;
import com.datamelt.port.in.person.FindPersonUseCase;

import java.util.Optional;

public class TestAdapterService
{
    private final FindPersonUseCase findPersonUseCase;

    public TestAdapterService()
    {
        this.findPersonUseCase = SampleApplication.getFindPersonUseCase();
    }

    public Optional<FindPersonDto> findByName(TestRequest testRequest)
    {
        Optional<Person> person = findPersonUseCase.findByName(testRequest.lastname(), testRequest.firstname());
        return person.map(FindPersonMapper::getFindPersonDto);
    }


}
