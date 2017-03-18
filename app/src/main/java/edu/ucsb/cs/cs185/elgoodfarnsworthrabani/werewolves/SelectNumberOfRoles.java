package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SelectNumberOfRoles extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_number_of_roles_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Players.number_of_werewolves = Players.playerCount() / 6;
        if (Players.number_of_werewolves * 6 < Players.playerCount())
            Players.number_of_werewolves++;

        int number_of_roles_remaining = Players.playerCount() - Players.number_of_werewolves;

        if (number_of_roles_remaining > 5) {
            Players.number_of_investigators = 1;
            Players.number_of_doctors       = 1;
            Players.number_of_townspersons  = number_of_roles_remaining - 2;
        }
        else if (number_of_roles_remaining == 5) {
            Players.number_of_investigators = 0;
            Players.number_of_doctors       = 1;
            Players.number_of_townspersons  = number_of_roles_remaining - 1;
        } else {
            Players.number_of_investigators = 0;
            Players.number_of_doctors       = 0;
            Players.number_of_townspersons  = number_of_roles_remaining;
        }

        TextView total_number_of_players_textview = (TextView) findViewById(R.id.total_number_of_players_textview);
        String total_number_of_players_string = "Total Number of Players: " + Players.playerCount();
        total_number_of_players_textview.setText(total_number_of_players_string);

        final TextView roles_error_message = (TextView) findViewById(R.id.roles_error_message);
        roles_error_message.setVisibility(View.INVISIBLE);

        Button continue_to_view_roles_button = (Button) findViewById(R.id.continue_to_view_roles_button);

        continue_to_view_roles_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalSelectedRoles() == Players.playerCount()){
                    Players.refresh_roles = true;
                    Intent view_roles_intent = new Intent(context, PlayerRoles.class);
                    startActivity(view_roles_intent);
                }
                else {
                    roles_error_message.setVisibility(View.VISIBLE);
                }
            }
        });

        Button werewolves_minus_button     = (Button) findViewById(R.id.werewolves_minus_button);
        Button doctors_minus_button        = (Button) findViewById(R.id.doctors_minus_button);
        Button investigators_minus_button  = (Button) findViewById(R.id.investigators_minus_button);
        Button townspersons_minus_button   = (Button) findViewById(R.id.townspersons_minus_button);

        werewolves_minus_button.setOnClickListener(new MinusButtonListener());
        doctors_minus_button.setOnClickListener(new MinusButtonListener());
        investigators_minus_button.setOnClickListener(new MinusButtonListener());
        townspersons_minus_button.setOnClickListener(new MinusButtonListener());

        Button werewolves_add_button       = (Button) findViewById(R.id.werewolves_add_button);
        Button doctors_add_button          = (Button) findViewById(R.id.doctors_add_button);
        Button investigators_add_button    = (Button) findViewById(R.id.investigators_add_button);
        Button townspersons_add_button     = (Button) findViewById(R.id.townspersons_add_button);

        werewolves_add_button.setOnClickListener(new AddButtonListener());
        doctors_add_button.setOnClickListener(new AddButtonListener());
        investigators_add_button.setOnClickListener(new AddButtonListener());
        townspersons_add_button.setOnClickListener(new AddButtonListener());

        TextView number_of_werewolves_textview    = (TextView) findViewById(R.id.number_of_werewolves_textview);
        TextView number_of_investigators_textview = (TextView) findViewById(R.id.number_of_investigators_textview);
        TextView number_of_doctors_textview       = (TextView) findViewById(R.id.number_of_doctors_textview);
        TextView number_of_townsperson_textview   = (TextView) findViewById(R.id.number_of_townsperson_textview);

        number_of_werewolves_textview.setText(Integer.toString(Players.number_of_werewolves));
        number_of_investigators_textview.setText(Integer.toString(Players.number_of_investigators));
        number_of_doctors_textview.setText(Integer.toString(Players.number_of_doctors));
        number_of_townsperson_textview.setText(Integer.toString(Players.number_of_townspersons));
    }

    public int totalSelectedRoles() {
        return Players.number_of_werewolves + Players.number_of_investigators + Players.number_of_doctors + Players.number_of_townspersons;
    }

    public void hideErrorMessageIfTotalsMatch() {
        TextView roles_error_message = (TextView) findViewById(R.id.roles_error_message);

        if (totalSelectedRoles() == Players.playerCount())
            roles_error_message.setVisibility(View.INVISIBLE);
    }

    public void updateTextView(int textview_id, String textview_string) {
        TextView textView = (TextView) findViewById(textview_id);
        textView.setText(textview_string);
    }

    public class MinusButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.werewolves_minus_button: {
                    if (Players.number_of_werewolves > 0) {
                        Players.number_of_werewolves--;
                        updateTextView(R.id.number_of_werewolves_textview, Players.number_of_werewolves + "");
                    }
                    break;
                }
                case R.id.doctors_minus_button: {
                    if (Players.number_of_doctors > 0) {
                        Players.number_of_doctors--;
                        updateTextView(R.id.number_of_doctors_textview, Players.number_of_doctors + "");
                    }
                    break;
                }
                case R.id.investigators_minus_button: {
                    if (Players.number_of_investigators > 0) {
                        Players.number_of_investigators--;
                        updateTextView(R.id.number_of_investigators_textview, Players.number_of_investigators + "");
                    }
                    break;
                }
                case R.id.townspersons_minus_button: {
                    if (Players.number_of_townspersons > 0) {
                        Players.number_of_townspersons--;
                        updateTextView(R.id.number_of_townsperson_textview, Players.number_of_townspersons + "");
                    }
                    break;
                }
            }
        }
    }

    public class AddButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (totalSelectedRoles() < Players.playerCount()) {
                switch (v.getId()) {
                    case R.id.werewolves_add_button: {
                        Players.number_of_werewolves++;
                        updateTextView(R.id.number_of_werewolves_textview, Players.number_of_werewolves + "");
                        break;
                    }
                    case R.id.doctors_add_button: {
                        Players.number_of_doctors++;
                        updateTextView(R.id.number_of_doctors_textview, Players.number_of_doctors + "");
                        break;
                    }
                    case R.id.investigators_add_button: {
                        Players.number_of_investigators++;
                        updateTextView(R.id.number_of_investigators_textview, Players.number_of_investigators + "");
                        break;
                    }
                    case R.id.townspersons_add_button: {
                        Players.number_of_townspersons++;
                        updateTextView(R.id.number_of_townsperson_textview, Players.number_of_townspersons + "");
                        break;
                    }
                }
            }
            hideErrorMessageIfTotalsMatch();
        }
    }
}
