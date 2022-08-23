import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class Wordle {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String yellowSq = new String(Character.toChars(0x1F7E8));
    public static final String greenSq = new String(Character.toChars(0x1F7E9));
    public static final String blackSq = new String(Character.toChars(0x2B1B));

    static ArrayList<ArrayList<String>> emojis = new ArrayList<>();

    public static void game(String word, int numberOfGuesses) {
        Scanner myScan = new Scanner(System.in);
        String[] inputArr;

        System.out.print("\nGive me your guess: ");

        String[] guessArr = word.split("");

        String input = myScan.next().toLowerCase();
        inputArr = input.split("");

        if (input.length() == 5) {
            if (word.equals(input)) {
                numberOfGuesses += 1;
                ArrayList<String> line = new ArrayList<>();

                System.out.println(ANSI_GREEN + input + ANSI_RESET);
                System.out.println("Congratulations! You have guessed the word in " + numberOfGuesses + " attempts");

                for (String ignored : guessArr) {
                    line.add(greenSq);
                }

                emojis.add(line);

            } else if (numberOfGuesses < 5) {
                numberOfGuesses += 1;
                ArrayList<String> line = new ArrayList<>();

                for (int i = 0; i < inputArr.length; i++) {
                    if (inputArr[i].equals(guessArr[i])) {
                        System.out.print(ANSI_GREEN + inputArr[i] + ANSI_RESET);
                        line.add(greenSq);

                    } else if (Arrays.asList(guessArr).contains(inputArr[i])) {
                        System.out.print(ANSI_YELLOW + inputArr[i] + ANSI_RESET);
                        line.add(yellowSq);

                    } else {
                        System.out.print(inputArr[i]);
                        line.add(blackSq);

                    }


                }
                emojis.add(line);

                System.out.println(" You have " + (6 - numberOfGuesses) + " guesses left");

                game(word, numberOfGuesses);
            } else {
                System.out.println("You have used all your attempts, good luck next time!");
                System.out.println("The word was: " + word);
            }
        } else {
            System.out.println("Make sure that you entered a 5-letter word!");
            game(word, numberOfGuesses);
        }

    }

    public static void getEmojis() {
        StringBuilder outcome = new StringBuilder();

        for (ArrayList<String> line : emojis) {
            outcome.append(line.toString().replace("[", "").replace("]", "").replace(",", "")).append("\n");
        }

        System.out.println(outcome);

        StringSelection stringSelection = new StringSelection(outcome.toString());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

    }
}

