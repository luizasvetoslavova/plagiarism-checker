package controller;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

// TODO Improve result text quality
public class GoogleSearch {
    public HashSet<PageResult> loadFirstPages(String query, int pageCount) {
        HashSet<PageResult> pages = new HashSet<>();

        for(int i = 0; i < pageCount; i++) {
            pages.add(loadPage(query, i));
        }
        return pages;
    }

    // TODO fix NullPointerException
    private static PageResult loadPage(String query, int resultIndex) {
        try {
            String encodedQuery = encodeQuery(query);
            String url = "https://www.google.com/search?q=" + encodedQuery;
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            Elements searchResults = doc.select("div.g");

            if (resultIndex < searchResults.size()) {
                Element result = searchResults.get(resultIndex);
                String link = extractLink(result);
                if (!link.isEmpty()) {
                    try {
                        Document doc2 = Jsoup.connect(link).ignoreContentType(true).get();
                        return new PageResult(link, doc2.text());
                    } catch (HttpStatusException e) {
                        System.out.println("Error connecting to the link: " + e.getStatusCode());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String encodeQuery(String query) {
        try {
            return URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String extractLink(Element result) {
        Element linkElement = result.selectFirst("a[href]");
        if (linkElement != null) {
            return linkElement.attr("href");
        }
        return "";
    }
}