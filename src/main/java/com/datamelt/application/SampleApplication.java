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
import java.util.function.Function;
import java.util.stream.Collectors;

public class SampleApplication
{
    // easyDI dependency injection
    private static EasyDI easyDI;
    private static Map<ProgramArgument,String> programArguments;

    // mappers for program arguments
    private static Function<String[], ProgramArgument> keyMapper = stringArray -> ProgramArgument.valueOf(stringArray[0].toUpperCase());
    private static Function<String[], String> valueMapper = stringArray -> stringArray[1];

    public static void main(String[] args) throws Exception
    {
        // process program arguments
        programArguments = processProgramArguments(args);

        // process configuration from yaml file
        MainConfiguration programConfiguration = loadConfiguration();

        SampleApplication application = new SampleApplication();
        // setup dependency injection
        application.setupDI(programConfiguration);

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
                .collect(Collectors.toMap(keyMapper, valueMapper));
    }

    private void setupDI(MainConfiguration mainConfiguration)
    {
        easyDI = new EasyDI();
        easyDI.bindInterface(PersonUseCase.class, PersonService.class);
        easyDI.bindInterface(PersonRepository.class, InMemoryPersonRepository.class);
        easyDI.bindInstance(ObjectMapper.class, new ObjectMapper());
        easyDI.bindInstance(MainConfiguration.class, mainConfiguration);
    }

    private static MainConfiguration loadConfiguration()
    {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        mapper.enable(DeserializationFeature.USE_LONG_FOR_INTS);
        try
        {
            return mapper.readValue(new File(programArguments.get(ProgramArgument.CONFIG)), MainConfiguration.class);
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
