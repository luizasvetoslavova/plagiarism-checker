package controller;

public class Main {
    public static void main(String[] args) {
        String text1 = "The Athenian philosopher Plato (c.428-347 B.C.) is one of the most important figures of the" +
                " Ancient Greek world and the entire history of Western thought. In his written dialogues he conveyed" +
                " and expanded on the ideas and techniques of his teacher Socrates. " +
                "" +
                "The Academy he founded was by accounts the world’s first university and in it he trained his" +
                " greatest student, the equally influential philosopher Aristotle. Plato’s recurring fascination was" +
                " the distinction between ideal forms and everyday experience, and how it played out both for" +
                " individuals and for societies. In his most famous work, he envisioned a civilization" +
                " governed not by lowly appetites but by the pure wisdom of a philosopher.";

//        slightly altered text
        String text2 = "The Athenian philosopher Plato is one of the most important figures of the" +
                " Ancient Greek world and the history of Western thought. In his written dialogues he expressed" +
                " and expanded on the ideas and techniques of his teacher Socrates. " +
                "" +
                "The Academy he founded was the world’s first university and in it he trained his" +
                " great student, the equally influential philosopher - Aristotle. Plato’s recurring fascination was" +
                " the distinction between ideal forms and the everyday experience, and how it played out both for" +
                " individuals and societies. In his most famous work, he envisioned a civilization," +
                " governed not by lowly appetites but by pure wisdom.";

        //not plagiarized
        String text3 = "Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. It is a general-purpose programming language intended to let programmers write once, run anywhere (WORA),[17] meaning that compiled Java code can run on all platforms that support Java without the need to recompile.[18] Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar to C and C++, but has fewer low-level facilities than either of them. The Java runtime provides dynamic capabilities (such as reflection and runtime code modification) that are typically not available in traditional compiled languages. As of 2019, Java was one of the most popular programming languages in use according to GitHub,[citation not found][19][20] particularly for client–server web applications, with a reported 9 million developers.[21]";

        //new ProgramRunner(text1,1,5, 20, 10).run();
    }
}