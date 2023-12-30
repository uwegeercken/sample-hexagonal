package com.datamelt.adapter.model;

public record TestRequest(String lastname, String firstname)
{
    public String getFullname()
    {
        return lastname + ", " + firstname;
    }
}
