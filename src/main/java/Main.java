
public class Main {
    public static void main(String[] args) {
        String para = "The Athenian philosopher Plato (c.428-347 B.C.) is one of the most important figures of the" +
                " Ancient Greek world and the entire history of Western thought. In his written dialogues he conveyed" +
                " and expanded on the ideas and techniques of his teacher Socrates. " +
                "" +
                "The Academy he founded was by some accounts the world’s first university and in it he trained his" +
                " greatest student, the equally influential philosopher Aristotle. Plato’s recurring fascination was" +
                " the distinction between ideal forms and everyday experience, and how it played out both for" +
                " individuals and for societies. In his most famous work, he envisioned a civilization" +
                " governed not by lowly appetites but by the pure wisdom of a philosopher-king.";

        String search = "where to find many books";
        new GoogleSearch().loadFirstPages(search, 5).forEach(page -> System.out.println(page.getLink()));
    }
}

//         - user inputs up to 8000 characters (about 1500-2000 words)
//         - scan scan scan
//         - app shows plagiarism percentage
//         - app shows links from where the text is plagiarized, if it is
//         - plagiarized phrases
//         ---------
//         - extracts 10 random phrases of 10-15 words from the string
//         - runs a Google search on each phrase
//         - loads the content of the 5 top pages for each phrase 10x5=50 pages
//         - cleans the content - keeps just the main text of the pages
//         - compares the content of each page with the content of the string. uses different content similarity metrics
//         - returns similarity percentage
//         - returns the similar phrases
//         - returns the original url's