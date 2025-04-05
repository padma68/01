import java.util.Random;
import java.util.Scanner;

public class TypingSpeedTest {

    public static void main(String[] args) {
        // Array of sample sentences
        String[] sentences = {
            "The quick brown fox jumps over the lazy dog.",
            "Java programming is both fun and challenging.",
            "Always strive to improve your coding skills.",
            "Coding is the language of the future."
        };

        // Scanner to capture user input
        Scanner scanner = new Scanner(System.in);

        boolean retry = true;
        while (retry) {
            // Randomly select a sentence
            Random rand = new Random();
            String sentenceToType = sentences[rand.nextInt(sentences.length)];

            // Display sentence for user to type
            System.out.println("Type the following sentence:");
            System.out.println(sentenceToType);

            // Start the timer
            long startTime = System.currentTimeMillis();

            // Get user input
            String userInput = scanner.nextLine();

            // End the timer
            long endTime = System.currentTimeMillis();
            long timeTaken = endTime - startTime; // Time in milliseconds

            // Calculate WPM
            double timeInMinutes = timeTaken / 60000.0; // Convert to minutes
            int wordCount = sentenceToType.split(" ").length; // Count words in sentence
            double wpm = wordCount / timeInMinutes;

            // Calculate accuracy
            int errors = countErrors(sentenceToType, userInput);
            double accuracy = ((sentenceToType.length() - errors) / (double) sentenceToType.length()) * 100;

            // Display results
            System.out.println("Your typing speed is: " + wpm + " WPM");
            System.out.println("Your typing accuracy is: " + accuracy + "%");

            // Provide feedback based on accuracy
            if (accuracy > 95) {
                System.out.println("Excellent! Keep it up!");
            } else if (accuracy > 85) {
                System.out.println("Great job! You're doing well.");
            } else {
                System.out.println("Keep practicing! You'll improve with time.");
            }

            // Ask if the user wants to retry
            System.out.println("Do you want to try again? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                retry = false;
            }
        }
    }

    // Function to count the number of errors in the user input
    public static int countErrors(String original, String input) {
        int errors = 0;
        int minLength = Math.min(original.length(), input.length());

        // Count errors for matching characters
        for (int i = 0; i < minLength; i++) {
            if (original.charAt(i) != input.charAt(i)) {
                errors++;
            }
        }

        // Add remaining characters if any
        errors += Math.abs(original.length() - input.length());

        return errors;
    }
}
