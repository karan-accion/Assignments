import java.util.Random;
import java.util.Scanner;

class NumberGuess {
    private static final int DEFAULT_MAX = 100;

    public static void main(String[] args) {
        Random rand = new Random();
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Welcome to the Number Guessing Game! Type 'quit' anytime to exit.");
            boolean playAgain = true;
            while (playAgain) {
                int max = DEFAULT_MAX;

                System.out.print("Enter max range (e.g. 100) or press Enter for default: ");
                String line = sc.nextLine().trim();
                if (!line.isEmpty()) {
                    try {
                        int parsed = Integer.parseInt(line);
                        if (parsed > 1) {
                            max = parsed;
                        } else {
                            System.out.println("Range must be greater than 1. Using default " + DEFAULT_MAX + ".");
                        }
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid range input. Using default " + DEFAULT_MAX + ".");
                    }
                }

                int secretNumber = rand.nextInt(max);
                int attempts = 0;
                System.out.println("Guess the number between 0 and " + (max - 1) + ".");

                while (true) {
                    System.out.print("Enter your guess: ");
                    String token = sc.nextLine().trim();
                    if (token.equalsIgnoreCase("quit")) {
                        playAgain = false;
                        break;
                    }
                    int guess;
                    try {
                        guess = Integer.parseInt(token);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Error: Please enter a valid integer or 'quit'.");
                        continue;
                    }

                    if (guess < 0 || guess >= max) {
                        System.out.println("Error: Guess must be between 0 and " + (max - 1));
                        continue;
                    }

                    attempts++;
                    if (guess < secretNumber) {
                        System.out.println("Too low! Try again.");
                    } else if (guess > secretNumber) {
                        System.out.println("Too high! Try again.");
                    } else {
                        System.out.println("Congratulations! You've guessed the number in " + attempts + " attempts.");
                        break;
                    }
                }

                if (!playAgain) break;
                System.out.print("Play again? (yes/no): ");
                String again = sc.nextLine().trim();
                if (!again.equalsIgnoreCase("yes") && !again.equalsIgnoreCase("y")) {
                    playAgain = false;
                }
            }
        }
    }
}