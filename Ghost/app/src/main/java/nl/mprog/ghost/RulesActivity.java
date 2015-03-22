package nl.mprog.ghost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class RulesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
    }

    public void onPlayButtonClick(View view) {
        Intent getSettingsScreenIntent = new Intent(this, SettingActivity.class);
        startActivity(getSettingsScreenIntent);
    }
}
