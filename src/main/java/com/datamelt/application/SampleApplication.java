package com.datamelt.application;

import com.datamelt.adapter.in.TestAdapter;
import com.datamelt.adapter.out.InMemoryPersonRepository;
import com.datamelt.domain.FindPersonService;
import com.datamelt.port.in.person.FindPersonUseCase;
import com.datamelt.port.out.PersonRepository;

public class SampleApplication
{
    public static void main(String[] args)
    {
        TestAdapter testAdapter = new TestAdapter();
        testAdapter.testFindPersons();

    }

    public static FindPersonUseCase getFindPersonUseCase()
    {
        return new FindPersonService();
    }

    public static PersonRepository getPersonRepository()
    {
        return new InMemoryPersonRepository();
    }
}
