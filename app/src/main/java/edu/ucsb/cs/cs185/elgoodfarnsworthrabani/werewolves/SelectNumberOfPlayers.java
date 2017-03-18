package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SelectNumberOfPlayers extends AppCompatActivity {
    public SelectNumberOfPlayersAdapter pa;
    public Button plus_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_number_of_players);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final TextView players_error_message = (TextView) findViewById(R.id.players_error_message);
        players_error_message.setVisibility(View.INVISIBLE);

        final TextView number_of_players_textview = (TextView) findViewById(R.id.number_of_players_textview);
        number_of_players_textview.setText(Integer.toString(Players.playerCount()));

        plus_button = (Button) findViewById(R.id.add_button);

        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Players.add(new Players.Player(true));
                number_of_players_textview.setText(Integer.toString(Players.playerCount()));
            }
        });

        ListView myList = (ListView) findViewById(R.id.list);
        pa = new SelectNumberOfPlayersAdapter();
        Players.setAdapter(pa);
        myList.setAdapter(pa);

        Button clear_all_players_button = (Button) findViewById(R.id.clear_all_players_button);
        clear_all_players_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Players.clear();
                number_of_players_textview.setText(Integer.toString(Players.playerCount()));
                players_error_message.setVisibility(View.INVISIBLE);
            }
        });

        final Button finish = (Button) findViewById(R.id.finish_number_of_players_button);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Players.playerCount() > 0) {
                    if (Players.verifyInput()) {
                        Intent select_number_of_roles_intent = new Intent(context, SelectNumberOfRoles.class);
                        startActivity(select_number_of_roles_intent);
                    } else {
                        System.out.println("ERRRRORR!!!!");
                        players_error_message.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }
}
