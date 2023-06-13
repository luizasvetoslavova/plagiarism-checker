package controller;

import java.util.*;

public class ProgramRunner {
    String text;
    int phraseCount;
    int pageCount;
    int wordCountPerPhrase;
    double percentageThreshold;

    public ProgramRunner(String text, int phraseCount, int pageCount, int wordCountPerPhrase, double percentageThreshold) {
        this.text = text;
        this.phraseCount = phraseCount;
        this.pageCount = pageCount;
        this.wordCountPerPhrase = wordCountPerPhrase;
        this.percentageThreshold = percentageThreshold;
    }

    public void run() {
        showPlagiarized(new PhraseExtractor(wordCountPerPhrase).getRandomPhrases(text, phraseCount),
                new GoogleSearch(), new SimilarityChecker(percentageThreshold));
    }

    private void showPlagiarized(List<String> phrases, GoogleSearch googleSearch, SimilarityChecker similarityChecker) {
        for (int i = 0; i < phraseCount; i++) {
            googleSearch.loadFirstPages(phrases.get(i), pageCount)
                    .forEach(page -> {
                        if (similarityChecker.isPlagiarized(text, page)) {
                            showResults(page);
                        }
                    });
        }
    }

    //TODO frontend
    private void showResults(PageResult page) {
        System.out.println("Link: " + page.getUrl() + " ");
        System.out.println("Plagiarism percentage: " + page.getPlagiarismPercentage() + " \n");
    }
}