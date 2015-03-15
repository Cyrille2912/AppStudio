package nl.mprog.ghost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class RulesScreen extends Activity {

    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_screen);

        play = (Button)findViewById(R.id.rules_play_button);
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

}
