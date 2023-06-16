package controller;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

// TODO Improve result text quality
public class GoogleSearch {
    private static GoogleSearch instance = null;

    public static GoogleSearch getInstance() {
        if (instance == null) {
            instance = new GoogleSearch();
        }
        return instance;
    }

    private GoogleSearch() {
    }

    public HashSet<PageResult> loadFirstPages(String query, int pageCount) {
        HashSet<PageResult> pages = new HashSet<>();

        PageResult currentPage;
        int pageIndex = 0;

        while (true) {
            currentPage = loadPage(query, pageIndex);
            if (currentPage != null) {
                pages.add(currentPage);
            }
            if (pages.size() == pageCount) {
                break;
            }
            pageIndex++;
        }
        return pages;
    }

    private static PageResult loadPage(String query, int resultIndex) {
        try {
            String encodedQuery = encodeQuery(query);
            String url = "https://www.google.com/search?q=" + encodedQuery;
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            Elements searchResults = doc.select("div.g");

            if (resultIndex < searchResults.size()) {
                Element result = searchResults.get(resultIndex);
                String link = extractLink(result);
                if (link != null) {
                    try {
                        Document doc2 = Jsoup.connect(link).ignoreContentType(true).get();
                        return new PageResult(link, doc2.text());
                    } catch (HttpStatusException | SSLHandshakeException e) {
                        return null;
                    } catch (IOException f) {
                        return null;
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
        return null;
    }
}