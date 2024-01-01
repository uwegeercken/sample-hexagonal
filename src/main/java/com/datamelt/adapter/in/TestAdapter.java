package com.datamelt.adapter.in;

import com.datamelt.adapter.model.TestRequest;
import com.datamelt.port.in.person.FindPersonUseCase;

import java.util.List;

public class TestAdapter
{
    private final List<TestRequest> requests = List.of(
            new TestRequest("Lipton", "Larry"),
            new TestRequest("Ponty", "Paul"),
            new TestRequest("Pragger", "Jill")
    );

    private final TestAdapterService testAdapterService;


    public TestAdapter(FindPersonUseCase findPersonUseCase)
    {
        this.testAdapterService = new TestAdapterService(findPersonUseCase);
    }

    public void testFindPersons()
    {
        requests.forEach(request ->
                testAdapterService.findByName(request).ifPresentOrElse(System.out::println,() ->
                    System.out.println("requested person not found: " + request.getFullname())));
    }
}
