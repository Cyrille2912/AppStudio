package com.appstudio.cyrille.random_guess_game;

import android.app.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.os.Bundle;


public class MainActivity extends Activity {

    private int rand_number;
    private int userInput;

    public void buttonClickListener() {
        Button submit = (Button)findViewById(R.id.button1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed1 = (EditText)findViewById(R.id.number_input);
                String empty = ed1.getText().toString();
                if (empty.matches("")) {
                    Toast.makeText(MainActivity.this, "Please enter an integer.", Toast.LENGTH_SHORT).show();
                }
                else {
                    userInput = Integer.parseInt(ed1.getText().toString());
                    check(rand_number, userInput);
                }
            }
        });

        Button instruct = (Button)findViewById(R.id.button2);
        instruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast toast = new Toast(getApplicationContext());
                Toast.makeText(MainActivity.this, "I just picked a random number between 1 and 1000. Try and find it!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void randomPick() {
        rand_number = new Random().nextInt(999)+1;
    }

    private void check(int a, int b) {
        if (a > b) {
            Toast.makeText(this, "Wrong! Hint: Try higher!", Toast.LENGTH_SHORT).show();
        }
        else if (a < b) {
            Toast.makeText(this, "Wrong! Hint: Try lower!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Well done! You found it!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        randomPick();
        buttonClickListener();
    }

}
