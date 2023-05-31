package controller;

import java.util.*;

public class ProgramRunner {
    String text;
    int phraseCount;
    int minWordCount;
    int maxWordCount;
    int pageCount;

    public ProgramRunner(String text) {
        this.phraseCount = 1;
        this.minWordCount = 15;
        this.maxWordCount = 20;
        this.pageCount = 1;
        this.text = text;
    }

    public void runProgram() {
        List<String> plagLinks = new ArrayList<>();
        List<Double> plagPercentages = new ArrayList<>();

//        GoogleSearch googleSearch = new GoogleSearch();
//        List<String> phrases = new PhraseExtractor().getRandomPhrases(text, phraseCount, minWordCount, maxWordCount);
//        TextComparer textComparer = new TextComparer();

        sortPlagiarized(new PhraseExtractor().getRandomPhrases(text, phraseCount, minWordCount, maxWordCount),
                new GoogleSearch(), new TextComparer(), plagLinks, plagPercentages);
        printResults(plagLinks, plagPercentages);
    }

    private void sortPlagiarized(List<String> phrases, GoogleSearch googleSearch, TextComparer textComparer,
                                 List<String> plagLinks, List<Double> plagPercentages) {
        for (int i = 0; i < phraseCount; i++) {
            googleSearch.loadFirstPages(phrases.get(i), pageCount)
                    .forEach(page -> {
                        if (textComparer.isPlagiarized(text, page)) {
                            plagLinks.add(page.getLink());
                            plagPercentages.add(textComparer.plagiarismPercentage(text, page));
                        }
                    });
        }
    }

    private void printResults(List<String> plagLinks, List<Double> plagPercentages) {
        for (int i = 0; i < plagLinks.size(); i++) {
            System.out.println("Link: " + plagLinks.get(i) + " ");
            System.out.println("Plagiarism percentage: " + plagPercentages.get(i) + " \n");
        }
    }
}