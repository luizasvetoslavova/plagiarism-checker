package controller;

public class PageResult {

    private final String link;
    private final String text;
    private TextStatus textStatus;

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

    public TextStatus getTextStatus() {
        return textStatus;
    }

    public void setTextStatus(TextStatus textStatus) {
        this.textStatus = textStatus;
    }
}