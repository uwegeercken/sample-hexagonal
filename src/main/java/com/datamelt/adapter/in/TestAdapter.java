package com.datamelt.adapter.in;

import com.datamelt.domain.model.Person;
import com.datamelt.port.in.person.FindPersonUseCase;

public class TestAdapter
{
    private final FindPersonUseCase findPersonUseCase;

    public TestAdapter(FindPersonUseCase findPersonUseCase)
    {
        this.findPersonUseCase = findPersonUseCase;
    }

    public void findPerson()
    {
        Person person = findPersonUseCase.findByName("Geercken", "Uwe");
    }
}
