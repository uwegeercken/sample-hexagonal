package com.datamelt.application;

import com.datamelt.adapter.in.TestAdapter;
import com.datamelt.adapter.out.InMemoryPersonRepository;
import com.datamelt.application.config.MainConfiguration;
import com.datamelt.domain.PersonService;
import com.datamelt.port.in.person.PersonUseCase;
import com.datamelt.port.out.PersonRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import eu.lestard.easydi.EasyDI;

public class SampleApplication
{
    private static EasyDI easyDI;


    public static void main(String[] args) throws Exception
    {
        // setup dependency injection
        setupDI();

        //MainConfiguration mainConfiguration = easyDI.getInstance(ConfigurationMapper.class).getConfiguration("/home/uwe/development/testconfiguration.yml");
        MainConfiguration mainConfiguration = easyDI.getInstance(MainConfiguration.class);
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
        //easyDI.bindInstance(ConfigurationMapper.class, new ConfigurationMapper(new ObjectMapper(new YAMLFactory())));
        easyDI.bindProvider(MainConfiguration.class,() -> {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
            mapper.enable(DeserializationFeature.USE_LONG_FOR_INTS);
            return new ConfigurationMapper(mapper).getConfiguration("/home/uwe/development/testconfiguration.yml");
        });
    }


}
