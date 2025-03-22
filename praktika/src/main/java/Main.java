import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner doubleSc = new Scanner(System.in);
        Scanner stringSc = new Scanner(System.in);
        double a = doubleSc.nextDouble();
        String operation = stringSc.nextLine();
        double b = doubleSc.nextDouble();
        double result = -1;
        boolean thereIsError = false;
        if (operation.equals("/") && b == 0) {
            System.out.println("ne stava");
        } else {
            switch (operation) {
                case "+": result = a + b; break;
                case "-": result = a - b; break;
                case "*": result = a * b; break;
                case "/": result = a / b; break;
                default: thereIsError = true;
                System.out.println("error!");
            }
            if (!thereIsError) System.out.println(result);
        }
    }
}
