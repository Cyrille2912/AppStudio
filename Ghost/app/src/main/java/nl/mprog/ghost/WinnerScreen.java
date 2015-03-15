package nl.mprog.ghost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class WinnerScreen extends Activity {

    private Button scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_screen);

        scores = (Button)findViewById(R.id.highScoresButton);
        TextView winner = (TextView)findViewById(R.id.winnerTextView);
        TextView reasonForWin = (TextView)findViewById(R.id.reasonWinningTextView);

        Intent WinnerInformation = getIntent();

        if (WinnerInformation != null) {
            String winnerName = WinnerInformation.getExtras().getString("WinnerName");
            String reasonWin = WinnerInformation.getExtras().getString("ReasonWin");
            winner.setText(winnerName + ",");
            reasonForWin.setText("Reason for winning: " + reasonWin);
        }

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
