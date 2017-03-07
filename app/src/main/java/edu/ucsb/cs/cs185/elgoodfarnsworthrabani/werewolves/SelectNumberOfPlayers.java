package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SelectNumberOfPlayers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_number_of_players);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
