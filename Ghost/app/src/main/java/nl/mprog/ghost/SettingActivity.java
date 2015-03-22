package nl.mprog.ghost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class SettingActivity extends Activity {

    private Spinner spinnerPlayer1;
    private Spinner spinnerPlayer2;
    private Spinner spinnerDictionary;
    private EditText player1Name;
    private EditText player2Name;

    public ArrayList<String> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        usersList = new ArrayList<>();
        if (savedInstanceState != null) {
            usersList = savedInstanceState.getStringArrayList("PLAYERS_LIST");
        }

        Set<String> usersListSet = getPreferences(Context.MODE_PRIVATE).getStringSet("USERS_LIST", null);
        if (usersListSet != null) {
            usersList = new ArrayList<>(usersListSet);
        }

        player1Name = (EditText)findViewById(R.id.edit_text_player1);
        player2Name = (EditText)findViewById(R.id.edit_text_player2);

        spinnerPlayer1 = (Spinner)
                findViewById(R.id.spinner_player1);
        spinnerPlayer2 = (Spinner)
                findViewById(R.id.spinner_player2);

        ArrayAdapter<String> spinnerPlayerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, usersList);
        spinnerPlayerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPlayerAdapter.notifyDataSetChanged();
        spinnerPlayer1.setAdapter(spinnerPlayerAdapter);
        spinnerPlayer2.setAdapter(spinnerPlayerAdapter);

        addItemsToDictionarySpinner();
        addListenerToSpinner();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putStringArrayList("PLAYERS_LIST", usersList);
        super.onSaveInstanceState(outState);
    }

    private void savePlayerList() {
        SharedPreferences.Editor sPEditor = getPreferences(Context.MODE_PRIVATE).edit();
        Set<String> usersListSet = new HashSet<>(usersList);

        sPEditor.putStringSet("USERS_LIST", usersListSet);
        sPEditor.apply();
    }

    @Override
    protected void onStop() {
        savePlayerList();
        super.onStop();
    }

    public void addItemsToDictionarySpinner() {
        spinnerDictionary = (Spinner)
                findViewById(R.id.spinner_dictionary);

        ArrayAdapter<CharSequence> spinnerDictionaryAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.language_dictionary, android.R.layout.simple_spinner_item);
        spinnerDictionaryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDictionary.setAdapter(spinnerDictionaryAdapter);
    }

    public void addListenerToSpinner() {
        spinnerPlayer1 = (Spinner)findViewById(R.id.spinner_player1);
        spinnerPlayer2 = (Spinner)findViewById(R.id.spinner_player2);
        spinnerDictionary = (Spinner)findViewById(R.id.spinner_dictionary);

        spinnerPlayer1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            EditText player1Name = (EditText)findViewById(R.id.edit_text_player1);
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner = parent.getItemAtPosition(position).toString();
                player1Name.setText(itemSelectedInSpinner);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerPlayer2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            EditText player2Name = (EditText)findViewById(R.id.edit_text_player2);
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner = parent.getItemAtPosition(position).toString();
                player2Name.setText(itemSelectedInSpinner);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerDictionary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void onPlayButtonClick(View view) {
        Intent getGamePlayIntent = new Intent(this, GamePlayActivity.class);

        player1Name = (EditText) findViewById(R.id.edit_text_player1);
        player2Name = (EditText) findViewById(R.id.edit_text_player2);
        final int MAX_LENGTH = 1;

        player1Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String[] userInputWords = s.toString().split(" ");
                String[] userInputNewLine = s.toString().split("\n");
                if (userInputWords.length > MAX_LENGTH || userInputNewLine.length > MAX_LENGTH) {
                    Toast.makeText(SettingActivity.this, "Please enter a valid name!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        player2Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String[] userInputWords = s.toString().split(" ");
                String[] userInputNewLine = s.toString().split("\n");
                if (userInputWords.length > MAX_LENGTH || userInputNewLine.length > MAX_LENGTH) {
                    Toast.makeText(SettingActivity.this, "Please enter a valid name!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        String player1 = player1Name.getText().toString();
        player1 = player1.substring(0,1).toUpperCase() + player1.substring(1);
        String player2 = player2Name.getText().toString();
        player2 = player2.substring(0,1).toUpperCase() + player2.substring(1);

        if (player1.equals(player2)) {
            if (!usersList.contains(player1)) {
                usersList.add(player1);
            }
        }
        else {
            if (!usersList.contains(player1)) {
                usersList.add(player1);
            }
            if (!usersList.contains(player2)) {
                usersList.add(player2);
            }
        }
        savePlayerList();

        String language = spinnerDictionary.getSelectedItem().toString();
        getGamePlayIntent.putExtra("LanguageDictionary", language);
        getGamePlayIntent.putExtra("Player1Name", player1);
        getGamePlayIntent.putExtra("Player2Name", player2);

        startActivity(getGamePlayIntent);
        finish();
    }
}
