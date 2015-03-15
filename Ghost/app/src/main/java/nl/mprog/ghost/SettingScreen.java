package nl.mprog.ghost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;



public class SettingScreen extends Activity {

    private Spinner spinnerPlayer1;
    private Spinner spinnerPlayer2;
    private Spinner spinnerDictionary;
    private ArrayAdapter<CharSequence> spinnerPlayerAdapter;
    private EditText player1Name;
    private EditText player2Name;

    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);

        player1Name = (EditText)findViewById(R.id.edit_text_player1);
        player2Name = (EditText)findViewById(R.id.edit_text_player2);
        playButton = (Button)findViewById(R.id.play_setting_button);
        addItemsToPlayerSpinner();
        addItemsToDictionarySpinner();
        addListenerToSpinner();
    }

    public void addItemsToPlayerSpinner() {
        spinnerPlayer1 = (Spinner)
                findViewById(R.id.spinner_player1);
        spinnerPlayer2 = (Spinner)
                findViewById(R.id.spinner_player2);

        spinnerPlayerAdapter =
                ArrayAdapter.createFromResource(this,
                R.array.playerNamesSpinner, android.R.layout.simple_spinner_item);
        spinnerPlayerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPlayer1.setAdapter(spinnerPlayerAdapter);
        spinnerPlayer2.setAdapter(spinnerPlayerAdapter);
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
        spinnerPlayer1 = (Spinner)
                findViewById(R.id.spinner_player1);
        spinnerPlayer2 = (Spinner)
                findViewById(R.id.spinner_player2);
        spinnerDictionary = (Spinner)
                findViewById(R.id.spinner_dictionary);

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
                String itemSelectedInSpinner = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onPlayButtonClick(View view) {
        final Intent getGamePlayIntent = new Intent(this,GamePlay.class);
        final String[] NameList = getResources().getStringArray(R.array.playerNamesSpinner);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1Name = (EditText)findViewById(R.id.edit_text_player1);
                player2Name = (EditText)findViewById(R.id.edit_text_player2);
                String player1 = player1Name.getText().toString();
                String player2 = player2Name.getText().toString();

                for (String aNameList : NameList) {
                    if (!player1.equals(aNameList) && !player1.equals(player2)) {
                        spinnerPlayerAdapter.add(player1);
                        spinnerPlayerAdapter.notifyDataSetChanged();
                    }
                    if (!player2.equals(aNameList) && !player2.equals(player1)) {
                        spinnerPlayerAdapter.add(player2);
                        spinnerPlayerAdapter.notifyDataSetChanged();
                    }
                }
                spinnerPlayer1.setAdapter(spinnerPlayerAdapter);
                spinnerPlayer2.setAdapter(spinnerPlayerAdapter);
                spinnerPlayerAdapter.notifyDataSetChanged();

                String language = spinnerDictionary.getSelectedItem().toString();
                getGamePlayIntent.putExtra("LanguageDictionary", language);
                getGamePlayIntent.putExtra("Player1Name", player1);
                getGamePlayIntent.putExtra("Player2Name", player2);
                startActivity(getGamePlayIntent);
                finish();
            }
        });
    }
}
