package nl.mprog.ghost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;


public class ScoresActivity extends Activity {

    HighScore highScore;
    ArrayList<Player> highScoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        highScore = new HighScore(getApplicationContext());

        String[] positions = {"1.", "2.", "3.", "4.", "5."};

        highScoreList = highScore.getHighScoreList();

        ArrayList<String> namesFromList = highScore.getNames(highScoreList);
        ArrayList<Integer> scoresFromList = highScore.getScores(highScoreList);
        ArrayList<String> positionsList = new ArrayList<>(Arrays.asList(positions));

        ListView playerPositions = (ListView)findViewById(R.id.positionsListView);
        ArrayAdapter<String> playerPositionsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, positionsList);
        playerPositions.setAdapter(playerPositionsAdapter);
        playerPositions.setTextFilterEnabled(true);
        playerPositions.deferNotifyDataSetChanged();


        ListView namesList = (ListView)findViewById(R.id.highScoreListView);
        ArrayAdapter<String> namesListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, namesFromList);
        namesList.setAdapter(namesListAdapter);
        namesList.setTextFilterEnabled(true);
        namesList.deferNotifyDataSetChanged();

        ListView scoresList = (ListView)findViewById(R.id.scoresListView);
        ArrayAdapter<Integer> scoresListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoresFromList);
        scoresList.setAdapter(scoresListAdapter);
        scoresList.setTextFilterEnabled(true);
        scoresList.deferNotifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        highScore.saveHighScoreList(highScoreList);
        super.onStop();
    }

    public void onNewGameButtonClick(View view) {
        Intent getNewGameScreenIntent = new Intent(this, SettingActivity.class);
        startActivity(getNewGameScreenIntent);
    }

    public void onHomeButtonClick(View view) {
        Intent getHomeScreenIntent = new Intent(this, HomeActivity.class);
        startActivity(getHomeScreenIntent);
    }

}
