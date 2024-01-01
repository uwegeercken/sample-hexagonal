package com.datamelt.adapter.in;

import com.datamelt.adapter.model.PersonDto;
import com.datamelt.adapter.model.FindPersonRequest;
import com.datamelt.domain.model.Person;
import com.datamelt.port.in.person.PersonUseCase;

import java.time.LocalDate;
import java.util.List;

public class TestAdapter
{
    private final List<FindPersonRequest> requests = List.of(
            new FindPersonRequest("Lipton", "Larry"),
            new FindPersonRequest("Kube", "Mary"),
            new FindPersonRequest("Ponty", "Paul"),
            new FindPersonRequest("Pragger", "Jill"),
            new FindPersonRequest("Mason", "Bill")
    );

    private final TestAdapterService testAdapterService;

    public TestAdapter(PersonUseCase personUseCase, TestAdapterService testAdapterService)
    {
        this.testAdapterService = testAdapterService;
    }

    public void testFindPersons()
    {
        requests.forEach(request ->
                System.out.println("person search  : " + testAdapterService.findByName(request)));
    }

    public void testCreatePerson()
    {

        String personDto = testAdapterService.createPerson("Hendriks", "Billie", "1964-07-03");
        System.out.println("person create  : " + personDto);
    }

    public void testDeletePersonByName()
    {
        String personDto = testAdapterService.deletePersonByName("Hendriks", "Billie");
        System.out.println("person delete  : " + personDto );
    }

    public void testDeleteNonExistingPersonByName()
    {
        String personDto = testAdapterService.deletePersonByName("Hello", "Hugo");
        System.out.println("person delete  : " + personDto );
    }
}
