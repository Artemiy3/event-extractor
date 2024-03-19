package ru.artemiy3.eventextractor.service;

import org.springframework.stereotype.Service;
import ru.artemiy3.eventextractor.model.Event;
import ru.artemiy3.eventextractor.model.Sentence;

import java.util.ArrayList;
import java.util.List;

@Service
public class LLMService {

    public List<Event> getEvents(Sentence sentence) {
        return new ArrayList<>();
    }
}
