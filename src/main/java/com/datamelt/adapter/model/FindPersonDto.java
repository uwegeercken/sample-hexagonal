package com.datamelt.adapter.model;

import java.time.LocalDate;

public record FindPersonDto(String lastname, String firstname, int age)
{
    public String getFullname()
    {
        return lastname + ", " + firstname;
    }
    @Override
    public String toString()
    {
        return lastname + ", " + firstname + " - age: " + age;

    }
}
