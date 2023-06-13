package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PhraseExtractor {
    private final int wordCountPerPhrase;

    public PhraseExtractor(int wordCountPerPhrase) {
        this.wordCountPerPhrase = wordCountPerPhrase;
    }

    public List<String> getRandomPhrases(String text, int phraseCount) {
        List<String> randomPhrases = new ArrayList<>();

        for (int i = 0; i < phraseCount; i++) {
            randomPhrases.add(getRandomPhrase(text));
        }
        return randomPhrases;
    }

    private String getRandomPhrase(String text) {
        List<String> words = (Arrays.stream(text.split(" ")).collect(Collectors.toList()));
        int firstWordIndex = new Random().nextInt((words.size() - wordCountPerPhrase) + 1);

        StringBuilder randomPhrase = new StringBuilder();
        for (int i = 0; i < wordCountPerPhrase; i++) {
            randomPhrase.append(words.get(firstWordIndex + i)).append(" ");
        }
        return randomPhrase.toString();
    }
}