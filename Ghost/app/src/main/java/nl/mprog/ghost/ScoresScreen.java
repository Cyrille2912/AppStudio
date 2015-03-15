package nl.mprog.ghost;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ScoresScreen extends Activity {

    String[] names = {"Frank", "Cyrille", "Sean", "Joost", "Martin"};
    int[] scores = {3, 2, 1, 1, 0};

    private SharedPreferences gamePreferences;
    public static final String GAME_PREFS =  "HighScore";
    ArrayList<String> HighScores;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores_screen);

        home = (Button)findViewById(R.id.newGameButton);

        if (savedInstanceState != null){
            ArrayList<String> scoreList = savedInstanceState.getStringArrayList("SCORES");
            HighScores = new ArrayList<>(scoreList);
        }

        Intent WinnerInformation = getIntent();

        if (WinnerInformation != null ) {
            String winnerName = WinnerInformation.getExtras().getString("WinnerName");
            int winnerScore = WinnerInformation.getExtras().getInt("WinnerScore");

            insertScore(winnerName, winnerScore);
        }

        gamePreferences = this.getSharedPreferences(GAME_PREFS, this.MODE_PRIVATE);
        HighScores = new ArrayList<>();
        Set<String> HighScoreSet = gamePreferences.getStringSet("list", null);
        for (int i = 0; i < scores.length; i++) {
            HighScores.add((i+1) + ".     " + names[i] + "     " + scores[i]);
        }

        if (HighScoreSet != null) {
            HighScores = new ArrayList<>(HighScoreSet);
        }

        ListView myList = (ListView)findViewById(R.id.highScoreListView);
        ArrayAdapter<String> myListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, HighScores);
        myList.setAdapter(myListAdapter);
        myList.setTextFilterEnabled(true);
        myList.deferNotifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putStringArrayList("SCORES", HighScores);
        super.onSaveInstanceState(outState);
    }

    public void saveHighScoreList() {
        SharedPreferences.Editor spEditor = gamePreferences.edit();
        Set<String> HighScoreSet = new HashSet<>();
        HighScoreSet.addAll(HighScores);
        spEditor.putStringSet("list", HighScoreSet);
        spEditor.commit();
    }

    @Override
    protected void onStop() {

        saveHighScoreList();
        super.onStop();
    }

    public void onNewGameButtonClick(View view) {
        final Intent getNewGameScreenIntent = new Intent(this, HomeScreen.class);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(getNewGameScreenIntent);
            }
        });
    }

    public void insertScore(String name, int score){

        int i = 0;
        if (scores.length != 0 && names.length != 0) {
            while (i < scores.length && scores[i] > score) {
                i++;
            }
            if (i < scores.length) {
                for (int j = scores.length - 1; j > i; j--) {
                    scores[j] = scores[j - 1];
                    names[j] = names[j - 1];
                }
                scores[i] = score;
                names[i] = name;
            }
        }
        else {
            scores[i] = score;
            names[i] = name;
        }
    }
}
