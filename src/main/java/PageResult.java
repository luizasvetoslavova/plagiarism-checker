public class PageResult {

    private String link;
    private String text;

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
}
