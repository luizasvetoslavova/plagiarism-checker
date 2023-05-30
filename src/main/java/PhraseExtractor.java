import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PhraseExtractor {

    private int maxWordCountToIndex;

    public List<String> getRandomPhrases(String text, int phraseCount, int minWordCount, int maxWordCount) {
        List<String> randomPhrases = new ArrayList<>();

        for (int i = 0; i < phraseCount; i++) {
            randomPhrases.add(getRandomPhrase(text, minWordCount, maxWordCount));
        }
        return randomPhrases;
    }

    private String getRandomPhrase(String text, int minWordCount, int maxWordCount) {
        List<String> words = (Arrays.stream(text.split(" ")).collect(Collectors.toList()));

        int firstWordIndex = new Random().nextInt(words.size() - maxWordCountToIndex);
        int wordCount = getRandomWordCount(minWordCount, maxWordCount);

        StringBuilder randomPhrase = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            randomPhrase.append(words.get(firstWordIndex + i)).append(" ");
        }
        return randomPhrase.toString();
    }

    private int getRandomWordCount(int min, int max) {
        this.maxWordCountToIndex = max - 1;
        return new Random().nextInt(max + 1 - min) + min;
    }
}