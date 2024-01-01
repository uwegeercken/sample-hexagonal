package com.datamelt.adapter.model;

import java.time.LocalDate;

public record FindPersonDto(String lastname, String firstname, int age)
{
    @Override
    public String toString()
    {
        return lastname + ", " + firstname + " - age: " + age;

    }
}
