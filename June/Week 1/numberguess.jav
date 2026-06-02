import java.util.*;
public class NumberGuess{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int number = rand.nextInt(100);
        int attempts = 0;
        System.out.println("Welcome to the Number Guessing Game!");
        while(true){
            System.out.print("Enter your guess (0-99): ");
            int guess = sc.nextInt();
            attempts++;
            if(guess < number){
                System.out.println("Too low! Try again.");
            } else if(guess > number){
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You've guessed the number in " + attempts + " attempts.");
                break;
            }
        }
    }
}