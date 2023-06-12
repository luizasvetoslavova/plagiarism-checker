package controller;

import java.util.*;

public class ProgramRunner {
    String text;
    int phraseCount;
    int pageCount;
    int minWordCount;
    int maxWordCount;
    double percentageThreshold;

    public ProgramRunner(String text) {
        this.text = text;
        this.phraseCount = 1;
        this.pageCount = 1;
        this.minWordCount = 15;
        this.maxWordCount = 20;
        this.percentageThreshold = 50;
    }

    public void runProgram() {
        showPlagiarized(new PhraseExtractor().getRandomPhrases(text, phraseCount, minWordCount, maxWordCount),
                new GoogleSearch(), new TextComparer(percentageThreshold));
    }

    private void showPlagiarized(List<String> phrases, GoogleSearch googleSearch, TextComparer textComparer) {
        for (int i = 0; i < phraseCount; i++) {
            googleSearch.loadFirstPages(phrases.get(i), pageCount)
                    .forEach(page -> {
                        if (textComparer.isPlagiarized(text, page)) {
                            printResults(page);
                        }
                    });
        }
    }

    //TODO frontend
    private void printResults(PageResult page) {
        System.out.println("Link: " + page.getLink() + " ");
        System.out.println("Plagiarism percentage: " + page.getPlagiarismPercentage() + " \n");
    }
}