import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

public class GoogleSearch {
    // TODO Improve result text quality
    // TODO fix MalformedURLException

    public HashSet<PageResult> loadFirstPages(String query, int pageCount) {
        HashSet<PageResult> pages = new HashSet<>();

        for(int i = 0; i < pageCount; i++) {
            pages.add(loadPage(query, i));
        }
        return pages;
    }

    private PageResult loadPage(String query, int resultIndex) {
        try {
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
            String url = "https://www.google.com/search?q=" + encodedQuery;
            Document doc = Jsoup.connect(url).get();
            Elements searchResults = doc.select("div.g");

            Element result = searchResults.get(resultIndex);
            String link = result.select("a[href]").attr("href");
            Document doc2 = Jsoup.connect(link).get();
            return new PageResult(link, doc2.text());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}