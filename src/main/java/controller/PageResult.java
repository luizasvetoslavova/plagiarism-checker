package controller;

public class PageResult {
    private final String url;
    private final String text;
    private double plagiarismPercentage;

    public PageResult(String link, String text) {
        this.url = link;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    public void setPlagiarismPercentage(double plagiarismPercentage) {
        this.plagiarismPercentage = plagiarismPercentage;
    }

    public double getPlagiarismPercentage() {
        return plagiarismPercentage;
    }
}