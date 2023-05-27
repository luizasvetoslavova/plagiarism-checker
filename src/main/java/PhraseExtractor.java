import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PhraseExtractor {

    public List<String> getRandomPhrases(String text) {
        List<String> randomPhrases = new ArrayList<>();

        for(int i = 0; i < 15; i++) {
            randomPhrases.add(getRandomPhrase(text));
        }
        return randomPhrases;
    }

    private String getRandomPhrase(String text) {
        List<String> words = (Arrays.stream(text.split(" ")).collect(Collectors.toList()));

        int firstWordIndex = new Random().nextInt(words.size() - 14);
        int wordCount = getRandomWordCount();

        StringBuilder randomPhrase = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            randomPhrase.append(words.get(firstWordIndex + i)).append(" ");
        }
        return randomPhrase.toString();
    }

    private int getRandomWordCount() {
        return new Random().nextInt(15 + 1 - 10) + 10;
    }
}