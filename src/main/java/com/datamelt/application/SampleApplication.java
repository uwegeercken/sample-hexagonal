package com.datamelt.application;

import com.datamelt.adapter.in.TestAdapter;
import com.datamelt.adapter.out.InMemoryPersonRepository;
import com.datamelt.application.config.MainConfiguration;
import com.datamelt.application.config.ProgramArgument;
import com.datamelt.domain.PersonService;
import com.datamelt.port.in.person.PersonUseCase;
import com.datamelt.port.out.PersonRepository;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import eu.lestard.easydi.EasyDI;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class SampleApplication
{
    private static EasyDI easyDI;
    private static Map<ProgramArgument,String> configuration;

    public static void main(String[] args) throws Exception
    {
        configuration = processProgramArguments(args);

        // setup dependency injection
        setupDI();

        final TestAdapter testAdapter = easyDI.getInstance(TestAdapter.class);
        testAdapter.testFindPersons();
        testAdapter.testCreatePerson();
        testAdapter.testDeletePersonByName();
        testAdapter.testDeleteNonExistingPersonByName();
    }

    private static Map<ProgramArgument,String> processProgramArguments(String[] args)
    {
            return Arrays.stream(args)
                    .map(argument -> argument.split("="))
                    .filter(argument -> argument.length==2)
                    .collect(Collectors.toMap(argument-> ProgramArgument.valueOf(argument[0].toUpperCase()), argument->argument[1]));
    }

    private static void setupDI()
    {
        easyDI = new EasyDI();
        easyDI.bindInterface(PersonUseCase.class, PersonService.class);
        easyDI.bindInterface(PersonRepository.class, InMemoryPersonRepository.class);
        easyDI.bindInstance(ObjectMapper.class, new ObjectMapper());
        easyDI.bindProvider(MainConfiguration.class, SampleApplication::loadConfiguration);
    }

    private static MainConfiguration loadConfiguration()
    {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        mapper.enable(DeserializationFeature.USE_LONG_FOR_INTS);
        try
        {
            return mapper.readValue(new File(configuration.get(ProgramArgument.CONFIG)), MainConfiguration.class);
        }
        catch (StreamReadException e)
        {
            throw new RuntimeException(e);
        }
        catch (DatabindException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


}
