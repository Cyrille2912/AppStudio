package nl.mprog.ghost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import static android.widget.Toast.*;
import static android.widget.Toast.makeText;


public class GamePlay extends Activity {

    public Player player1;
    public Player player2;
    public int scorePlayer1;
    public int scorePlayer2;

    private EditText userInput;
    private TextView currentWord;
    private TextView playerName;
    private int currentPLayer;

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        Intent SettingsInformation = getIntent();
        String languagePreviousAct = SettingsInformation.getExtras().getString("LanguageDictionary");
        String player1Name = SettingsInformation.getExtras().getString("Player1Name");
        String player2Name = SettingsInformation.getExtras().getString("Player2Name");

        playerName = (TextView)findViewById(R.id.player_name);
        currentWord = (TextView)findViewById(R.id.word_fragment);
        userInput = (EditText)findViewById(R.id.user_input);

        if (savedInstanceState != null) {
            String player = savedInstanceState.getString("PLAYER");
            String current = savedInstanceState.getString("FRAGMENT");
            String input = savedInstanceState.getString("INPUT");

            playerName.setText(player);
            currentWord.setText(current);
            userInput.setText(input);

        }

        String sPPlayer = getPreferences(Context.MODE_PRIVATE).getString("PLAYER", "EMPTY");
        String sPCurrent = getPreferences(Context.MODE_PRIVATE).getString("FRAGMENT", "EMPTY");
        String sPInput = getPreferences(Context.MODE_PRIVATE).getString("INPUT", "EMPTY");

        if (!sPPlayer.equals("EMPTY")){
            playerName.setText(sPPlayer);
        }
        if (!sPCurrent.equals("EMPTY")){
            currentWord.setText(sPCurrent);
        }
        if (!sPInput.equals("EMPTY")){
            userInput.setText(sPInput);
        }

        player1 = new Player();
        player2 = new Player();

        currentPLayer = 1;
        player1.setName(player1Name);
        player2.setName(player2Name);
        scorePlayer1 = player1.getScore();
        scorePlayer2 = player2.getScore();

        game = new Game(this, player1, player2, game.getDictionary(languagePreviousAct));
        playerName.setText(player1.getName());
        currentWord.setText("");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("PLAYER", playerName.getText().toString());
        outState.putString("FRAGMENT", currentWord.getText().toString());
        outState.putString("INPUT", userInput.getText().toString());
        super.onSaveInstanceState(outState);
    }

    private void saveSettings() {
        SharedPreferences.Editor sPEditor = getPreferences(Context.MODE_PRIVATE).edit();

        sPEditor.putString("PLAYER", playerName.getText().toString());
        sPEditor.putString("FRAGMENT", currentWord.getText().toString());
        sPEditor.putString("INPUT", userInput.getText().toString());

        sPEditor.commit();
    }

    @Override
    protected void onStop() {

        saveSettings();
        super.onStop();
    }

    @Override
    protected void onPause() {

        saveSettings();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_game_play, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent getSettingsScreenIntent = new Intent(this, SettingScreen.class);
        switch (item.getItemId()) {
            case R.id.game_settings:
                startActivity(getSettingsScreenIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onSubmitClickButton(View view) {
        Button submit = (Button) findViewById(R.id.submit_button);
        final Intent getWinnerScreenIntent = new Intent(this, WinnerScreen.class);
        final Intent getScoresScreenIntent = new Intent(this, ScoresScreen.class);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInput = (EditText) findViewById(R.id.user_input);
                currentWord = (TextView) findViewById(R.id.word_fragment);
                String loser;
                Player winner;
                String wordToCheck = game.guessLetter(String.valueOf(userInput.getText()), String.valueOf(currentWord.getText()));

                if (!wordToCheck.equals("error")) {
                    currentWord.setText(wordToCheck.toUpperCase());

                    if (game.hasWon(wordToCheck) == 1 || game.hasWon(wordToCheck) == 2) {
                        loser = playerName.getText().toString();
                        winner = game.gameWinner(loser);

                        String word = String.valueOf(currentWord.getText());
                        getWinnerScreenIntent.putExtra("WinnerName", winner.getName());
                        getScoresScreenIntent.putExtra("WinnerName", winner.getName());
                        getScoresScreenIntent.putExtra("WinnerScore", winner.getScore());
                        if (game.hasWon(wordToCheck) == 1) {
                            String reasonWin = word + " is a word!";
                            getWinnerScreenIntent.putExtra("ReasonWin", reasonWin);
                        } else {
                            String reasonWin = word + " is not a word and can't become one!";
                            getWinnerScreenIntent.putExtra("ReasonWin", reasonWin);
                        }
                        startActivity(getWinnerScreenIntent);
                        finish();
                    } else {
                        if (currentPLayer == 1) {
                            playerName.setText(player2.getName());
                            currentPLayer = 2;
                        } else {
                            playerName.setText(player1.getName());
                            currentPLayer = 1;
                        }
                    }
                } else {
                    makeText(GamePlay.this, "Enter only a letter please.", LENGTH_SHORT).show();
                }
            }
        });
    }

}
