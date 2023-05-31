package controller;

import java.util.*;

public class ProgramRunner {
    String text;
    int phraseCount;
    int minWordCount;
    int maxWordCount;
    int pageCount;

    public ProgramRunner(String text) {
        this.phraseCount = 5;
        this.minWordCount = 15;
        this.maxWordCount = 20;
        this.pageCount = 5;
        this.text = text;
    }

    public String runProgram() {
        List<String> plagLinks = new ArrayList<>();
        List<Double> plagPercentages = new ArrayList<>();
        sortPlagiarized(new PhraseExtractor().getRandomPhrases(text, phraseCount, minWordCount, maxWordCount),
                new GoogleSearch(), new TextComparer(), plagLinks, plagPercentages);

        return getResults(plagLinks, plagPercentages);
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

    private String getResults(List<String> plagLinks, List<Double> plagPercentages) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plagLinks.size(); i++) {
            sb.append("Link: ").append(plagLinks.get(i)).append(", ")
                    .append("Plagiarism percentage: ").append(plagPercentages.get(i)).append(" \n");
        }
        return sb.toString();
    }
}