package com.datamelt.adapter.in;

import com.datamelt.adapter.model.TestRequest;
import com.datamelt.adapter.out.InMemoryPersonRepository;
import com.datamelt.domain.FindPersonService;

import java.util.List;

public class TestAdapter
{
    List<TestRequest> requests = List.of(
            new TestRequest("Lipton", "Larry"),
            new TestRequest("Ponty", "Paul")
    );

    private final TestAdapterService testAdapterService = new TestAdapterService();

    public void testFindPersons()
    {
        requests.forEach(request -> testAdapterService.findByName(request).ifPresentOrElse(System.out::println,() -> System.out.println("not found: " + request.getFullname())));
    }
}
