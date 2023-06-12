package controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

//TODO change similarity check method
public class TextComparer {
    private final double percentageThreshold;

    public TextComparer(double percentageThreshold) {
        this.percentageThreshold = percentageThreshold;
    }

    public boolean isPlagiarized(String text, PageResult pageResult) {
        double plagiarismPercentage = checkPlagiarismPercentage(text, pageResult.getText());
        pageResult.setPlagiarismPercentage(plagiarismPercentage);
        return plagiarismPercentage > percentageThreshold;
    }

    private double checkPlagiarismPercentage(String text1, String text2) {
        long hash1 = calculateSimHash(text1);
        long hash2 = calculateSimHash(text2);
        int distance = calculateHammingDistance(hash1, hash2);
        return (64.0 - distance) / 64.0 * 100.0;
    }

    private long calculateSimHash(String text) {
        Map<String, Integer> wordFrequencies = new HashMap<>();

        String[] words = text.toLowerCase().split("\\s+");

        for (String word : words) {
            int frequency = wordFrequencies.getOrDefault(word, 0);
            wordFrequencies.put(word, frequency + 1);
        }

        long[] wordHashes = new long[64];

        for (String word : wordFrequencies.keySet()) {
            byte[] wordHash = calculateHash(word);

            for (int i = 0; i < 64; i++) {
                if (((wordHash[i / 8] >> (7 - (i % 8))) & 1) == 1) {
                    wordHashes[i] += wordFrequencies.get(word);
                } else {
                    wordHashes[i] -= wordFrequencies.get(word);
                }
            }
        }

        long simHash = 0;
        for (int i = 0; i < 64; i++) {
            if (wordHashes[i] >= 0) {
                simHash |= (1L << (63 - i));
            }
        }

        return simHash;
    }

    private int calculateHammingDistance(long hash1, long hash2) {
        long xor = hash1 ^ hash2;
        int distance = 0;
        while (xor != 0) {
            distance++;
            xor &= (xor - 1);
        }
        return distance;
    }

    private byte[] calculateHash(String word) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(word.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}