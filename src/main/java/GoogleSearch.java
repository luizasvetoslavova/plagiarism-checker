
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class GoogleSearch {
    public void search() {
        try {
            String url = "https://www.google.com/search?q=" + "run over the mill sentence europe margin flight".replace(" ", "+");
            Document doc = Jsoup.connect(url).get();
            Elements searchResults = doc.select("div.g");

            if (!searchResults.isEmpty()) {
                Element firstResult = searchResults.first();
                String link = firstResult.select("a[href]").attr("href");
                System.out.println("Link: " + link);
                Document doc2 = Jsoup.connect(link).get();
                System.out.println(doc2.text());
            } else {
                System.out.println("No search results found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred during the search.");
        }
    }
}
