package com.datamelt.application;

import com.datamelt.application.config.MainConfiguration;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class ConfigurationMapper
{
    private ObjectMapper mapper;

    public ConfigurationMapper(ObjectMapper mapper)
    {
        this.mapper = mapper;
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        mapper.enable(DeserializationFeature.USE_LONG_FOR_INTS);
    }

    public MainConfiguration getConfiguration(String configurationFilename)
    {
        File config = new File(configurationFilename);
        try
        {
            return mapper.readValue(config, MainConfiguration.class);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}
