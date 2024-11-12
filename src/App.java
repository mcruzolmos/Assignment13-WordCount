import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the input file: ");
        String inputFilePath = scanner.nextLine();

        // Prompt the user for output file path
        System.out.print("Enter the path of the output file: ");
        String outputFilePath = scanner.nextLine();

        // Call the word count method
        countWords(inputFilePath, outputFilePath);

        // Close the scanner
        scanner.close();
    }

    public static void countWords(String inputFilePath, String outputFilePath) {
        // Create a map to store word counts (case-insensitive)
        Map<String, Integer> wordCounts = new HashMap<>();

        // Read the input file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;

            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Convert each word to lowercase
                String word = line.trim().toLowerCase();

                // If the word is not empty, update the count in the map
                if (!word.isEmpty()) {
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
            return;
        }

        // Write the results to the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the output file: " + e.getMessage());
        }

        System.out.println("Word count results have been written to: " + outputFilePath);
    }
}
