package com.datamelt.domain.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

public class Person
{
    private int id;
    private final String lastname;
    private final String firstname;
    private LocalDate dateOfBirth;

    public Person(String lastname, String firstname, LocalDate dateOfBirth)
    {
        this.lastname = lastname;
        this.firstname = firstname;
        this.dateOfBirth = dateOfBirth;
    }

    public Person(String lastname, String firstname)
    {
        this.lastname = lastname;
        this.firstname = firstname;
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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getAge()
    {
        if(dateOfBirth!=null)
        {
            return Period.between(dateOfBirth, LocalDate.now()).getYears();
        }
        else
        {
            return -1;
        }
    }

    public String getFullname()
    {
        return lastname + ", " + firstname;
    }

    public boolean hasEqualName(String lastname, String firstname)
    {
        return this.lastname.equals(lastname) && this.firstname.equals(firstname);
    }
}
