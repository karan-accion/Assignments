import java.util.*;
public class Calculator{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Advanced Calculator!");
        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();
        System.out.print("Enter second number: ");
        double num2 = sc.nextDouble();
        System.out.println("Select operation: +, -, *, /, ^, sqrt");
        String operation = sc.next();
        double result = 0;
        switch(operation){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if(num2 != 0){
                    result = num1 / num2;
                } else {
                    System.out.println("Error: Division by zero");
                    return;
                }
                break;
            case "^":
                result = Math.pow(num1, num2);
                break;
            case "sqrt":
                if(num1 >= 0){
                    result = Math.sqrt(num1);
                } else {
                    System.out.println("Error: Cannot calculate square root of a negative number");
                    return;
                }
                break;
            default:
                System.out.println("Invalid operation");
                return;
        }
        System.out.println("Result: " + result);
    }
}