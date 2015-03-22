package nl.mprog.ghost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class WinnerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

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
        Intent getScoresScreenIntent = new Intent(this, ScoresActivity.class);
        startActivity(getScoresScreenIntent);
    }
}
