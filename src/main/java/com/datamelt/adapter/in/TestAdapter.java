package com.datamelt.adapter.in;

import com.datamelt.adapter.model.CreatePersonDto;
import com.datamelt.adapter.model.FindPersonDto;
import com.datamelt.adapter.model.FindPersonRequest;
import com.datamelt.port.in.person.PersonUseCase;

import java.util.List;

public class TestAdapter
{
    private final List<FindPersonRequest> requests = List.of(
            new FindPersonRequest("Lipton", "Larry"),
            new FindPersonRequest("Ponty", "Paul"),
            new FindPersonRequest("Pragger", "Jill"),
            new FindPersonRequest("Mason", "Bill")
    );

    private final TestAdapterService testAdapterService;


    public TestAdapter(PersonUseCase personUseCase)
    {
        this.testAdapterService = new TestAdapterService(personUseCase);
    }

    public void testFindPersons()
    {
        requests.forEach(request ->
                testAdapterService.findByName(request).ifPresentOrElse(findPersonDto -> System.out.println("found person    : " + findPersonDto),() ->
                    System.out.println("person not found: " + request.getFullname())));
    }

    public void testCreatePerson()
    {
        CreatePersonDto createPersonDto = new CreatePersonDto("Hendriks", "Billie","1964-07-03");
        int personId = testAdapterService.createPerson(createPersonDto);
        System.out.println("person created  : " + createPersonDto.getFullname() + " - with id: " + personId);
    }

    public void testDeletePerson()
    {
        FindPersonDto findPersonDto = new FindPersonDto("Hendriks", "Billie",0);
        testAdapterService.deletePerson(findPersonDto);
        System.out.println("person deleted  : " + findPersonDto.getFullname() );
    }
}
