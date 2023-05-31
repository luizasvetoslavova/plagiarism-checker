package controller;

public class TextComparer {

    //TODO change similarity check method
    public boolean isPlagiarized(String text, PageResult pageResult) {
        int distance = calculateDistance(text, pageResult.getText());
        int maxLength = Math.max(text.length(), pageResult.getText().length());
        double similarity = 1.0 - ((double) distance / maxLength);

        boolean isPlagiarized = similarity * 100 > 10;
        setPageStatus(isPlagiarized, pageResult);

        return isPlagiarized;
    }

    public double plagiarismPercentage(String text, PageResult pageResult) {
        int distance = calculateDistance(text, pageResult.getText());
        int maxLength = Math.max(text.length(), pageResult.getText().length());
        double similarity = 1.0 - ((double) distance / maxLength);
        return similarity * 100;
    }

    private void setPageStatus(boolean isPlagiarized, PageResult pageResult) {
        if (isPlagiarized) {
            pageResult.setTextStatus(TextStatus.PLAGIARIZED);
        } else {
            pageResult.setTextStatus(TextStatus.UNIQUE);
        }
    }

    private static int calculateDistance(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 0; i <= text1.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= text2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}