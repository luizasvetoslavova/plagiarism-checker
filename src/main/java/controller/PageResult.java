package controller;

public class PageResult {
    private final String link;
    private final String text;
    private double plagiarismPercentage;

    public PageResult(String link, String text) {
        this.link = link;
        this.text = text;
    }

    public String getLink() {
        return link;
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