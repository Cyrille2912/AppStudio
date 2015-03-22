package nl.mprog.ghost;


import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;


public class HighScore {

    private SharedPreferences prefs;
    ArrayList<Player> highScoreList;
    final int MAX_LENGTH = 5;

    HighScore(Context ctx) {
        prefs = ctx.getSharedPreferences("HighScores", Context.MODE_PRIVATE);
        this.highScoreList = new ArrayList<>();
    }

    public ArrayList<Player> getHighScoreList() {
        Map<String, ?> map = prefs.getAll();

        if (map != null) {
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                this.highScoreList.add(new Player(entry.getKey(), Integer.parseInt(entry.getValue().toString())));
            }
        }
        Collections.sort(this.highScoreList, new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                int scorePlayer1 = player1.getScore();
                int scorePlayer2 = player2.getScore();

                if (scorePlayer1 > scorePlayer2) {
                    return -1;
                }
                if (scorePlayer1 < scorePlayer2) {
                    return 1;
                }
                return 0;
            }
        });
        return highScoreList;
    }

    public boolean allReadyInHighScore(Player winner, ArrayList<Player> highScoreList) {
        String winnerName = winner.getName();
        for (Player player : highScoreList) {
            String playerName = player.getName();
            if (playerName.equals(winnerName)) {
                return true;
            }
        }
        return false;
    }

    public void addToHighScore(Player player, ArrayList<Player> highScoreList) {
        int scorePlayer = player.getScore();

        if (highScoreList.size() != 0) {
            if (highScoreList.size() >= MAX_LENGTH) {
                Player lastPlayer = highScoreList.get(MAX_LENGTH);
                int scoreLastPlayer = lastPlayer.getScore();
                if (scorePlayer > scoreLastPlayer) {
                    highScoreList.set(MAX_LENGTH, player);
                }
            } else {
                highScoreList.add(player);
            }
        }
        else highScoreList.add(player);
    }

    public ArrayList<Player> updateHighScorePlayer(Player winner, ArrayList<Player> highScoreList) {
        String winnerName = winner.getName();
        for (Player player : highScoreList) {
            String playerName = player.getName();
            if(playerName.equals(winnerName)) {
                int playerScore = player.getScore();
                int winnerScore = winner.getScore();
                player.score = playerScore + winnerScore;
            }
        }
        return highScoreList;
    }

    public void saveHighScoreList(ArrayList<Player> list) {
        SharedPreferences.Editor sPEditor = prefs.edit();

        for (int i = 0; i < list.size(); i++) {
            sPEditor.putInt(list.get(i).getName(), list.get(i).getScore());
        }
        sPEditor.apply();
    }

    public ArrayList<String> getNames(ArrayList<Player> list) {
        ArrayList<String> namesList = new ArrayList<>();

        if (list.size() >= MAX_LENGTH) {
            for (int i = 0; i <= MAX_LENGTH; i++) {
                String playerName = list.get(i).getName();
                namesList.add(playerName);
            }
        }
        else {
            for (int i = 0; i < list.size(); i++) {
                String playerName = list.get(i).getName();
                namesList.add(playerName);
            }
        }
        return namesList;
    }

    public ArrayList<Integer> getScores(ArrayList<Player> list) {
        ArrayList<Integer> scoresList = new ArrayList<>();

        if (list.size() >= MAX_LENGTH) {
            for (int i = 0; i <= MAX_LENGTH; i++) {
                Integer playerScore = list.get(i).getScore();
                scoresList.add(playerScore);
            }
        }
        else {
            for (int i = 0; i < list.size(); i++) {
                Integer playerScore = list.get(i).getScore();
                scoresList.add(playerScore);
            }
        }
        return scoresList;
    }
}
