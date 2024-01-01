package com.datamelt.adapter.in;

import com.datamelt.adapter.model.PersonDto;

import java.util.List;

public class TestAdapter
{
    private final List<SamplePerson> requests = List.of(
            new SamplePerson("Lipton", "Larry"),
            new SamplePerson("Kube", "Mary"),
            new SamplePerson("Ponty", "Paul"),
            new SamplePerson("Pragger", "Jill"),
            new SamplePerson("Mason", "Bill")
    );

    private final TestAdapterService testAdapterService;

    public TestAdapter(TestAdapterService testAdapterService)
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

        PersonDto personDto = testAdapterService.createPerson("Hendriks", "Billie", "1964-07-03");
        System.out.println("person create  : " + personDto);
    }

    public void testDeletePersonByName()
    {
        PersonDto personDto = testAdapterService.deletePersonByName("Hendriks", "Billie");
        System.out.println("person delete  : " + personDto );
    }

    public void testDeleteNonExistingPersonByName()
    {
        PersonDto personDto = testAdapterService.deletePersonByName("Hello", "Hugo");
        System.out.println("person delete  : " + personDto );
    }
}
