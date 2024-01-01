package com.datamelt.application;

import com.datamelt.adapter.in.TestAdapter;
import com.datamelt.adapter.out.InMemoryPersonRepository;
import com.datamelt.domain.PersonService;
import com.datamelt.port.in.person.PersonUseCase;
import com.datamelt.port.out.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.lestard.easydi.EasyDI;

public class SampleApplication
{
    private static EasyDI easyDI;
    public static void main(String[] args)
    {
        // setup dependency injection
        setupDI();

        final TestAdapter testAdapter = easyDI.getInstance(TestAdapter.class);
        testAdapter.testFindPersons();
        testAdapter.testCreatePerson();
        testAdapter.testDeletePersonByName();
        testAdapter.testDeleteNonExistingPersonByName();
    }

    private static void setupDI()
    {
        easyDI = new EasyDI();
        easyDI.bindInterface(PersonUseCase.class, PersonService.class);
        easyDI.bindInterface(PersonRepository.class, InMemoryPersonRepository.class);
        easyDI.bindInstance(ObjectMapper.class, new ObjectMapper());
    }
}
