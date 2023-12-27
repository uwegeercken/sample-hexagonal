package com.datamelt.domain.model;

import java.time.LocalDate;

public class Person
{
    String lastname;
    String firstname;
    LocalDate dateOfBirth;

    public Person(String lastname, String firstname, LocalDate dateOfBirth)
    {
        this.lastname = lastname;
        this.firstname = firstname;
        this.dateOfBirth = dateOfBirth;
    }

    public String getLastname()
    {
        return lastname;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }
}
