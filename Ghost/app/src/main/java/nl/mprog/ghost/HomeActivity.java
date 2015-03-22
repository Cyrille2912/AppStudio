package nl.mprog.ghost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onPlayButtonClick(View view) {
        Intent getSettingsScreenIntent = new Intent(this, SettingActivity.class);
        startActivity(getSettingsScreenIntent);
    }

    public void onRulesButtonClick(View view) {
        Intent getRulesScreenIntent = new Intent(this, RulesActivity.class);
        startActivity(getRulesScreenIntent);
    }

    public void onScoresButtonClick(View view) {
        Intent getScoresScreenIntent = new Intent(this, ScoresActivity.class);
        startActivity(getScoresScreenIntent);
    }
}
