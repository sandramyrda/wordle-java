public class Main {
    public static void main(String[] args) {

        System.out.println("WELCOME TO WORDLE\nYour task is to guess the 5-letter word\nYou have 6 attempts");

        Wordle.game(RandomWord.getWord(), 0);

        System.out.println("Your result has been copied into your clipboard");

        Wordle.getEmojis();

        // ask if the player wants to play again and provide a scanner to input the choice


    }
}
