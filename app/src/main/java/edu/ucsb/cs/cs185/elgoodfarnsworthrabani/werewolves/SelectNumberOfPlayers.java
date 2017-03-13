package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectNumberOfPlayers extends AppCompatActivity {
    int numPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_number_of_players);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        numPlayers = 0;
        final TextView tv = (TextView) findViewById(R.id.number_of_players_textview);
        tv.setText(Integer.toString(numPlayers));

        Button plus = (Button) findViewById(R.id.add_button);
        Button minus = (Button) findViewById(R.id.minus_button);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numPlayers = numPlayers + 1;
                tv.setText(Integer.toString(numPlayers));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numPlayers>0) {
                    numPlayers = numPlayers - 1;
                    tv.setText(Integer.toString(numPlayers));
                }
            }
        });
    }
}
