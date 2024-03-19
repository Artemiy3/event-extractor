package ru.artemiy3.eventextractor.service;

import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.artemiy3.eventextractor.model.Event;
import ru.artemiy3.eventextractor.model.Sentence;
import ru.artemiy3.eventextractor.model.SourceText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventExtractionService {

    @Value("${language}")
    private static String LANGUAGE;

    @Autowired
    private LLMService llmService;
    private final SentenceDetector sentenceDetector = new SentenceDetectorME(LANGUAGE);
    private final Tokenizer tokenizer = new TokenizerME(LANGUAGE);
    private final POSTaggerME posTagger = new POSTaggerME(LANGUAGE);

    public EventExtractionService() throws IOException {
    }

    public List<Event> extractEvents(SourceText sourceText) {
        List<Sentence> sentences = extractSentencesFromText(sourceText.getContent());
        List<Sentence> sentencesWithVerbs = filterSentencesWithVerbs(sentences);

        return new ArrayList<>();
    }

    public List<Sentence> extractSentencesFromText(String text){

        String[] sentences = sentenceDetector.sentDetect(text);

        return Arrays.stream(sentences).map(Sentence::new).collect(Collectors.toList());
    }

    private List<Sentence> filterSentencesWithVerbs(List<Sentence> sentences) {
        List<Sentence> result = new ArrayList<>();

        for (Sentence sentence: sentences) {
            String stringSentence = sentence.text();

            String[] tokens = tokenizer.tokenize(stringSentence);
            String[] tags = posTagger.tag(tokens);

            long verbsCount = Arrays.stream(tags).filter(t -> t.equals("VERB")).count();
            if (verbsCount > 0)
                result.add(sentence);
        }

        return result;
    }
}
