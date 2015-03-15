package nl.mprog.ghost;


import android.content.Context;

public class Game {

    Player player1;
    Player player2;
    Dictionary dictionary;
    int english = R.raw.english;
    int dutch = R.raw.dutch;

    public Game(Context ctx, Player player1, Player player2, int language) {
        this.player1 = player1;
        this.player2 = player2;

        dictionary = new Dictionary(ctx,language);
    }

    public String guessLetter(String userInput, String wordFragment) {
        userInput = userInput.toLowerCase();
        wordFragment = wordFragment.toLowerCase();
        if (!wordFragment.equals("")) {
            if (userInput.trim().length() != 0 && userInput.trim().length() < 2) {
                return wordFragment + userInput;
            }
            else return "error";
        }
        else {
            return userInput;
        }
    }

    public int hasWon(String wordToCheck) {
        if (dictionary.checkInDictionary(wordToCheck)) {
            if (wordToCheck.trim().length() == 3 && dictionary.hasOtherWordContaining(wordToCheck)) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (dictionary.hasWordsStartingWith(wordToCheck)) {
                return 0;
            } else return 2;
        }
    }

    public Player gameWinner(String loser) {
        if (loser.equals(player1.getName())) {
            int player2score = player2.getScore();
            player2.setScore(player2score + 1);
            return player2;
        }
        else {
            int player1score = player1.getScore();
            player1.setScore(player1score + 1);
            return player1;
        }
    }

    public int getDictionary(String languageDictionary) {
        if (languageDictionary.equals("English")) {
            return english;
        }
        else {
            return dutch;
        }
    }
}
