package controller;

public class Main {
    public static void main(String[] args) {
        String query = "The Athenian philosopher Plato (c.428-347 B.C.) is one of the most important figures of the" +
                " Ancient Greek world and the entire history of Western thought. In his written dialogues he conveyed" +
                " and expanded on the ideas and techniques of his teacher Socrates. " +
                "" +
                "The Academy he founded was by accounts the world’s first university and in it he trained his" +
                " greatest student, the equally influential philosopher Aristotle. Plato’s recurring fascination was" +
                " the distinction between ideal forms and everyday experience, and how it played out both for" +
                " individuals and for societies. In his most famous work, he envisioned a civilization" +
                " governed not by lowly appetites but by the pure wisdom of a philosopher.";

        // slightly altered query
//        String para2 = "The Athenian philosopher Plato is one of the most important figures of the" +
//                " Ancient Greek world and the history of Western thought. In his written dialogues he expressed" +
//                " and expanded on the ideas and techniques of his teacher Socrates. " +
//                "" +
//                "The Academy he founded was the world’s first university and in it he trained his" +
//                " great student, the equally influential philosopher - Aristotle. Plato’s recurring fascination was" +
//                " the distinction between ideal forms and the everyday experience, and how it played out both for" +
//                " individuals and societies. In his most famous work, he envisioned a civilization," +
//                " governed not by lowly appetites but by pure wisdom.";

        new ProgramRunner(query).runProgram();
    }
}