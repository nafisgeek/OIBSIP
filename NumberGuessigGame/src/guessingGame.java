import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
    public static void main(String[] args) {
        playGame();
    }

    public static void playGame() {  
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(); 
        int maxAttempts = 5;
        int rounds = 0;
        int wins = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        while (true) {
            int targetNumber = random.nextInt(100) + 1;
            int attempts = 0;
            rounds++;

            System.out.println("I've chosen a number between 1 and 100. Can you guess it?");
            
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                try {
                    int guess = scanner.nextInt();
                    attempts++;

                    if (guess < targetNumber) {
                        System.out.println("Too low! Try again.");
                    } else if (guess > targetNumber) {
                        System.out.println("Too high! Try again.");
                    } else {
                        System.out.println("Congratulations! You've guessed the number " + targetNumber +
                                " correctly in " + attempts + " attempts!");
                        wins++;
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid number.");
                    scanner.next();
                }
            }

            if (attempts >= maxAttempts) {
                System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The correct number was " + targetNumber + ".");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;
            }
        }

        System.out.println("Game Over! You played " + rounds + " rounds and won " + wins + " times.");
        scanner.close();
    }
}