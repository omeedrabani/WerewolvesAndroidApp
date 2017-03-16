package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SelectNumberOfPlayers extends AppCompatActivity {
    public SelectNumberOfPlayersAdapter pa;
    public Button plus_button;
    public static TextView number_of_players_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_number_of_players);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        number_of_players_textview = (TextView) findViewById(R.id.number_of_players_textview);
        number_of_players_textview.setText(Integer.toString(Players.playerCount()));

        plus_button = (Button) findViewById(R.id.add_button);

        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Players.add(new Players.Player());
                number_of_players_textview.setText(Integer.toString(Players.playerCount()));
                pa.notifyDataSetChanged();
            }
        });

        ListView myList = (ListView) findViewById(R.id.list);
        pa = new SelectNumberOfPlayersAdapter();
        Players.setAdapter(pa);
        myList.setAdapter(pa);

        final Button finish = (Button) findViewById(R.id.finish_number_of_players_button);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                EditText last = (EditText) getCurrentFocus();
                last.clearFocus();
                finish.requestFocus();

                //for debugging purposes
                for (int i=0; i<numPlayers; i++) {
                    Log.d("NAME", pa.getItem(i).name );
                }
                */

                Intent view_roles_intent = new Intent(context, PlayerRoles.class);
                startActivity(view_roles_intent);
            }
        });

    }
}
