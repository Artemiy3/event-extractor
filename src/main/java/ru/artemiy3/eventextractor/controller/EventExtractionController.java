package ru.artemiy3.eventextractor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.artemiy3.eventextractor.model.Event;
import ru.artemiy3.eventextractor.model.Sentence;
import ru.artemiy3.eventextractor.model.SourceText;
import ru.artemiy3.eventextractor.service.EventExtractionService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventExtractionController {
    @Autowired
    EventExtractionService eventExtractionService;

    @RequestMapping(value="/health", method= RequestMethod.GET)
    public String checkHealth() {
        return "I'm healthy!";
    }

    @RequestMapping(value="/example", method= RequestMethod.GET)
    public Event getExampleEvent() {
        return new Event("Example description", 123);
    }

    @RequestMapping(value="/extract/events", method= RequestMethod.GET)
    public List<Event> extractEvents(@RequestBody SourceText sourceText) {
        return eventExtractionService.extractEvents(sourceText);
    }

    @RequestMapping(value="/sentences", method = RequestMethod.GET)
    public List<Sentence> getSentences(@RequestBody SourceText sourceText) {
        return eventExtractionService.extractSentencesFromText(sourceText.getContent());
    }


}
