package nl.mprog.ghost;


import android.content.Context;

public class Game {

    Player player1;
    Player player2;
    Dictionary dictionary;

    public Game(Context ctx, Player player1, Player player2, String language) {
        this.player1 = player1;
        this.player2 = player2;

        dictionary = new Dictionary(ctx,language);
    }

    public String guessLetter(String userInput, String wordFragment) {
        userInput = userInput.toLowerCase();
        wordFragment = wordFragment.toLowerCase();

        if (!wordFragment.equals("")) {
            if (userInput.length() != 0 && userInput.length() < 2) {
                return wordFragment + userInput;
            }
            else return "error";
        }
        else {
            return userInput;
        }
    }

    public String hasWon(String wordToCheck) {
        if (wordToCheck.trim().length() >= 3) {
            if (dictionary.checkInDictionary(wordToCheck)) {
                if (dictionary.hasOtherWordContaining(wordToCheck)) {
                    return "NOT_WON";
                }
                return "WORD";
            } else {
                if (dictionary.hasWordsStartingWith(wordToCheck)) {
                    return "NOT_WON";
                }
                return "NO_WORD";
            }
        } else {
            if (dictionary.hasWordsStartingWith(wordToCheck)) {
                return "NOT_WON";
            } else return "NO_WORD";
        }
    }

    public Player gameWinner(String loser) {
        if (loser.equals(player1.getName())) {
            return player2;
        }
        else {
            return player1;
        }
    }
}
