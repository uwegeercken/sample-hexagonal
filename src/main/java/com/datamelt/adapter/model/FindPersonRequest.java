package com.datamelt.adapter.model;

public record FindPersonRequest(String lastname, String firstname)
{
    public String getFullname()
    {
        return lastname + ", " + firstname;
    }
}
