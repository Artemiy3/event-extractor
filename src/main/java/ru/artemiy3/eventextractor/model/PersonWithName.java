package ru.artemiy3.eventextractor.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersonWithName extends Person {
    private String name;

    @Override
    public String getName() {
        return name;
    }
}
