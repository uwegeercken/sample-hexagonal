package com.datamelt.adapter.model;

import com.datamelt.adapter.in.Status;

public record PersonDto(int id, String lastname, String firstname, int age, Status status)
{
}
