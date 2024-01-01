package com.datamelt.adapter.in;

import com.datamelt.adapter.model.CreatePersonDto;
import com.datamelt.adapter.model.FindPersonRequest;
import com.datamelt.port.in.person.FindPersonUseCase;

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


    public TestAdapter(FindPersonUseCase findPersonUseCase)
    {
        this.testAdapterService = new TestAdapterService(findPersonUseCase);
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
}
