package ru.artemiy3.eventextractor.service;

import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import org.springframework.stereotype.Service;
import ru.artemiy3.eventextractor.model.Event;
import ru.artemiy3.eventextractor.model.Sentence;
import ru.artemiy3.eventextractor.model.SourceText;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventExtractionService {

    private final SentenceDetector sentenceDetector = new SentenceDetectorME("en");;

    public EventExtractionService() throws IOException {
    }

    public List<Event> extractEvents(SourceText sourceText) {
        return List.of(new Event(sourceText.getContent(), 1), new Event("d2", 2));
    }

    public List<Sentence> extractSentencesFromText(String text){

        String[] sentences = sentenceDetector.sentDetect(text);

        return Arrays.stream(sentences).map(Sentence::new).collect(Collectors.toList());
    }
}
