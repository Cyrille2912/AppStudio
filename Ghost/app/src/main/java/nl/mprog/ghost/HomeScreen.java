package nl.mprog.ghost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeScreen extends Activity {

    private Button play, rules, scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        play = (Button)findViewById(R.id.play_button);
        rules = (Button)findViewById(R.id.rules_button);
        scores = (Button)findViewById(R.id.scores_button);
    }

    public void onPlayButtonClick(View view) {

        final Intent getSettingsScreenIntent = new Intent(this, SettingScreen.class);
        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(getSettingsScreenIntent);
            }
        });
    }

    public void onRulesButtonClick(View view) {
        final Intent getRulesScreenIntent = new Intent(this, RulesScreen.class);
        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(getRulesScreenIntent);
            }
        });
    }

    public void onScoresButtonClick(View view) {
        final Intent getScoresScreenIntent = new Intent(this, ScoresScreen.class);
        scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(getScoresScreenIntent);
            }
        });
    }

}
