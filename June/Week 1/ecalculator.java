import java.util.Scanner;

class AdvancedCalculator {
    public static void main(String[] args) {
        System.out.println("Welcome to the Advanced Calculator! Type 'quit' to exit.");

        try (Scanner sc = new Scanner(System.in)) {
            boolean continueCalculating = true;

            while (continueCalculating) {
                try {
                    System.out.println("Select operation: +, -, *, /, %, ^, sqrt (or type quit)");
                    String operation = sc.next().trim().toLowerCase();
                    if (operation.equals("quit")) break;

                    double num1 = 0;
                    double num2 = 0;
                    boolean unary = operation.equals("sqrt");

                    if (!unary) {
                        Double a = readNumber(sc, "Enter first number: ");
                        if (a == null) continue;
                        num1 = a;

                        Double b = readNumber(sc, "Enter second number: ");
                        if (b == null) continue;
                        num2 = b;
                    } else {
                        Double a = readNumber(sc, "Enter number: ");
                        if (a == null) continue;
                        num1 = a;
                    }

                    try {
                        Double result = calculate(num1, num2, operation);
                        if (result != null) {
                            System.out.println("Result: " + result);
                        }
                    } catch (ArithmeticException ae) {
                        System.err.println("Math error: " + ae.getMessage());
                    }

                    System.out.print("Do another calculation? (yes/no): ");
                    String cont = sc.next().trim();
                    if (!cont.equalsIgnoreCase("yes") && !cont.equalsIgnoreCase("y")) {
                        continueCalculating = false;
                    }
                } catch (Exception ex) {
                    System.err.println("Unexpected error: " + ex.getMessage());
                    sc.nextLine();
                }
            }
        }

        System.out.println("Thank you for using the calculator!");
    }

    private static Double readNumber(Scanner sc, String prompt) {
        System.out.print(prompt);
        if (!sc.hasNextDouble()) {
            System.out.println("Error: Please enter a valid number.");
            sc.next();
            return null;
        }
        return sc.nextDouble();
    }

    private static Double calculate(double num1, double num2, String operation) {
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                }
                System.out.println("Error: Division by zero");
                return null;
            case "%":
                if (num2 != 0) {
                    return num1 % num2;
                }
                System.out.println("Error: Modulo by zero");
                return null;
            case "^":
                return Math.pow(num1, num2);
            case "sqrt":
                if (num1 >= 0) {
                    return Math.sqrt(num1);
                }
                System.out.println("Error: Cannot calculate square root of a negative number");
                return null;
            default:
                System.out.println("Error: Invalid operation");
                return null;
        }
    }
}