package com.datamelt.adapter.model;

public record CreatePersonDto(String lastname, String firstname, String dateOfBirth)
{
    public String getFullname()
    {
        return lastname + ", " + firstname;
    }
}
