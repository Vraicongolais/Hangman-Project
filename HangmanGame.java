import java.util.*;
import java.util.Random;

public class HangmanGame {
    static Scanner input;
    public static void main(String[] args){
        System.out.println("Hello, Welcome to the Java Hangman Game");
        hangman();
    }

    public static void hangman(){
        String[] words = {"python", "hangman", "programming", "challenge", "java"};

        Set<Character> correctGuesses = new HashSet<>();
        Set<Character> incorrectGuesses = new HashSet<>();
        int attempts = 6;

        Random random = new Random();
        String secret_word = words[random.nextInt(words.length)];
        System.out.println(secret_word);
        input = new Scanner(System.in);
        while(true){
            displayGameState(secret_word, correctGuesses, incorrectGuesses, attempts);
            System.out.println("Enter your guess: ");
            String userGuess = input.nextLine().toLowerCase();

            char letter = userGuess.charAt(0); // first character is taken as input

            if (secret_word.indexOf(letter) >= 0){
                correctGuesses.add(letter);

                // Check for win condition
                if (secret_word.chars().allMatch(c -> correctGuesses.contains((char) c))){
                    System.out.println("Congratulation! You've guessed the word!");
                    break;
                }
            }
            else {
                if (!incorrectGuesses.contains(letter)){
                    incorrectGuesses.add(letter);
                    attempts -- ;
                }
                // Check for lose condition
                if (attempts == 0){
                    System.out.println("Game over! You've run out of attempts.");
                    System.out.println("The secret word was: " + secret_word);
                    break;
                }
            }


        }
    }
    private static void displayGameState(String secret_word, Set<Character> correctGuesses, Set<Character> incorrectGuesses, int attempts){
        StringBuilder displayWord = new StringBuilder();

        for (char letter : secret_word.toCharArray()){
            if (correctGuesses.contains(letter)){
                displayWord.append(letter);
            }
            else{
                displayWord.append("_");
            }
        }
        System.out.println("Word: " + displayWord);
        System.out.println("Incorrect Guesses: " + incorrectGuesses);
        System.out.println("Attempts Left: " + attempts);
    }
}
