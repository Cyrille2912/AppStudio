package nl.mprog.ghost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static android.widget.Toast.*;
import static android.widget.Toast.makeText;


public class GamePlayActivity extends Activity {

    public Player player1;
    public Player player2;
    public int scorePlayer1;
    public int scorePlayer2;

    private EditText userInput;
    private TextView currentWord;
    private TextView playerName;
    private int currentPLayer;

    Game game;
    HighScore highScore;
    ArrayList<Player> highScoreList;

    SharedPreferences.Editor sPEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        playerName = (TextView)findViewById(R.id.player_name);
        currentWord = (TextView)findViewById(R.id.word_fragment);
        userInput = (EditText)findViewById(R.id.user_input);

        if (savedInstanceState != null) {
            playerName.setText(savedInstanceState.getString("PLAYER"));
            currentWord.setText(savedInstanceState.getString("FRAGMENT"));
            userInput.setText(savedInstanceState.getString("INPUT"));
            currentPLayer = savedInstanceState.getInt("CURRENT_PLAYER");
        }

        Intent SettingsInformation = getIntent();
        String languagePreviousAct = SettingsInformation.getExtras().getString("LanguageDictionary");
        String player1Name = SettingsInformation.getExtras().getString("Player1Name");
        String player2Name = SettingsInformation.getExtras().getString("Player2Name");

        player1 = new Player(player1Name, 0);
        player2 = new Player(player2Name, 0);

        currentPLayer = 1;
        scorePlayer1 = player1.getScore();
        scorePlayer2 = player2.getScore();

        game = new Game(this, player1, player2, languagePreviousAct);
        highScore = new HighScore(this);

        playerName.setText(player1.getName());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putString("PLAYER", playerName.getText().toString());
        outState.putString("FRAGMENT", currentWord.getText().toString());
        outState.putString("INPUT", userInput.getText().toString());
        outState.putInt("CURRENT_PLAYER", currentPLayer);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String sPPlayer = getPreferences(Context.MODE_PRIVATE).getString("PLAYER", "EMPTY");
        String sPCurrentFragment = getPreferences(Context.MODE_PRIVATE).getString("FRAGMENT", "EMPTY");
        String sPInput = getPreferences(Context.MODE_PRIVATE).getString("INPUT", "EMPTY");
        int sPCurrent = getPreferences(Context.MODE_PRIVATE).getInt("CURRENT_PLAYER", 0);

        if (!sPPlayer.equals("EMPTY")){
            playerName.setText(sPPlayer);
        }
        if (!sPCurrentFragment.equals("EMPTY")){
            currentWord.setText(sPCurrentFragment);
        }
        if (!sPInput.equals("EMPTY")){
            userInput.setText(sPInput);
        }
        if (sPCurrent != 0) {
            currentPLayer = sPCurrent;
        }
    }

    private void saveSettings() {
        sPEditor = getPreferences(Context.MODE_PRIVATE).edit();

        sPEditor.putString("PLAYER", playerName.getText().toString());
        sPEditor.putString("FRAGMENT", currentWord.getText().toString());
        sPEditor.putString("INPUT", userInput.getText().toString());
        sPEditor.putInt("CURRENT_PLAYER", currentPLayer);

        sPEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_game_play, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent getSettingsScreenIntent = new Intent(this, SettingActivity.class);
        switch (item.getItemId()) {
            case R.id.game_settings:
                startActivity(getSettingsScreenIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onSubmitClickButton(View view) throws InterruptedException {
        Intent getWinnerScreenIntent = new Intent(this, WinnerActivity.class);
        Intent getScoresScreenIntent = new Intent(this, ScoresActivity.class);

        String wordToCheck = game.guessLetter(String.valueOf(userInput.getText()), String.valueOf(currentWord.getText()));

        if (!wordToCheck.equals("error")) {
            currentWord.setText(wordToCheck.toUpperCase());

            if (game.hasWon(wordToCheck).equals("WORD") || game.hasWon(wordToCheck).equals("NO_WORD")) {
                String loser = playerName.getText().toString();
                Player winner = game.gameWinner(loser);
                winner.setScore(winner.getScore()+1);

                highScoreList = highScore.getHighScoreList();

                if (highScore.allReadyInHighScore(winner, highScoreList)) {
                    highScoreList = highScore.updateHighScorePlayer(winner, highScoreList);
                }
                else {
                    highScore.addToHighScore(winner, highScoreList);
                }
                highScore.saveHighScoreList(highScoreList);

                String word = String.valueOf(currentWord.getText());

                getWinnerScreenIntent.putExtra("WinnerName", winner.getName());
                getScoresScreenIntent.putExtra("WinnerName", winner.getName());
                getScoresScreenIntent.putExtra("WinnerScore", winner.getScore());

                if (game.hasWon(wordToCheck).equals("WORD")) {
                    String reasonWin = word + " is a word!";
                    getWinnerScreenIntent.putExtra("ReasonWin", reasonWin);
                } else {
                    String reasonWin = word + " is not a word and can't become one!";
                    getWinnerScreenIntent.putExtra("ReasonWin", reasonWin);
                }
                finish();
                startActivity(getWinnerScreenIntent);

            } else {
                if (currentPLayer == 1) {
                    Thread.sleep(1000);
                    makeText(GamePlayActivity.this, "Changing player...", LENGTH_SHORT).show();
                    playerName.setText(player2.getName());
                    userInput.setText("");
                    currentPLayer = 2;
                } else {
                    Thread.sleep(1000);
                    makeText(GamePlayActivity.this, "Changing player...", LENGTH_SHORT).show();
                    playerName.setText(player1.getName());
                    userInput.setText("");
                    currentPLayer = 1;
                }
            }
        } else {
            makeText(GamePlayActivity.this, "Enter only a letter please.", LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        saveSettings();
        super.onStop();
    }
}
