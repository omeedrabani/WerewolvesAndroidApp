package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;
import java.util.List;

public class SelectNumberOfPlayers extends AppCompatActivity {
    public Button plus_button;
    List<LinearLayout> player_input_name_linear_layouts = new ArrayList<LinearLayout>();

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
        final LinearLayout layout = (LinearLayout) findViewById(R.id.scrollable_layout);

        for (Players.Player p : Players.PLAYERS) {
            LinearLayout ll = createView(context, layout, number_of_players_textview, p.id, players_error_message);
            layout.addView(ll);
        }

        plus_button = (Button) findViewById(R.id.add_button);

        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Players.Player player =  new Players.Player(true);
                Players.add(player);
                number_of_players_textview.setText(Integer.toString(Players.playerCount()));
                LinearLayout ll = createView(context, layout, number_of_players_textview, player.id, players_error_message);
                layout.addView(ll);
            }
        });

        Button clear_all_players_button = (Button) findViewById(R.id.clear_all_players_button);
        clear_all_players_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Players.clear();

                for (LinearLayout ll : player_input_name_linear_layouts) {
                    layout.removeView(ll);
                }

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
                        players_error_message.setVisibility(View.INVISIBLE);
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

    private LinearLayout createView(Context c, LinearLayout layout, TextView tv, final int player_id, final TextView players_error_message){
        final LinearLayout main_layout = layout;
        final TextView number_of_players = tv;

        final LinearLayout ll = new LinearLayout(c);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(params);
        ll.setPadding(0,10,0,10);

        final EditText et = new EditText(c);
        LayoutParams et_params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        et_params.weight = 5.0f;
        et.setLayoutParams(et_params);
        et.setInputType(InputType.TYPE_CLASS_TEXT);
        et.setText(Players.getPlayer(player_id).name);

        et.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Players.setName(player_id, s.toString());
                if (Players.verifyInput()) {
                    players_error_message.setVisibility(View.INVISIBLE);
                } else {
                    players_error_message.setVisibility(View.VISIBLE);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        et.setSelectAllOnFocus(true);

        Button button = new Button(c);
        LayoutParams button_params = new LayoutParams((int) getResources().getDimension(R.dimen.half_button_width), LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(button_params);
        button.setPadding(0,40,0,0);
        Drawable top = ContextCompat.getDrawable(c, android.R.drawable.ic_delete);
        button.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Players.remove(player_id);
                number_of_players.setText(Integer.toString(Players.playerCount()));
                main_layout.removeView(ll);
            }
        });

        ll.addView(et);
        ll.addView(button);

        player_input_name_linear_layouts.add(ll);

        return ll;
    }
}


