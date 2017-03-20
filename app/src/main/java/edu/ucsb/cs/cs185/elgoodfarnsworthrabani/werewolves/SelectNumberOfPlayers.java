package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
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
    public SelectNumberOfPlayersAdapter pa;
    public Button plus_button;
    public int playerCount;
    List<EditText> et_list = new ArrayList<EditText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_number_of_players);
        playerCount = 0;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        pa = new SelectNumberOfPlayersAdapter();
        Players.setAdapter(pa);

        final TextView players_error_message = (TextView) findViewById(R.id.players_error_message);
        players_error_message.setVisibility(View.INVISIBLE);

        final TextView number_of_players_textview = (TextView) findViewById(R.id.number_of_players_textview);
        number_of_players_textview.setText(Integer.toString(Players.playerCount()));
        final LinearLayout layout = (LinearLayout) findViewById(R.id.scrollable_layout);

        plus_button = (Button) findViewById(R.id.add_button);

        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerCount++;
                Players.add(new Players.Player(true));
                number_of_players_textview.setText(Integer.toString(Players.playerCount()));
                LinearLayout ll = createView(context, layout, number_of_players_textview, pa);
                layout.addView(ll);
            }
        });

//        ListView myList = (ListView) findViewById(R.id.list);
        //pa = new SelectNumberOfPlayersAdapter();
        //Players.setAdapter(pa);
//        myList.setAdapter(pa);

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
                        for (int i=0; i<et_list.size(); i++) {
                            String name = et_list.get(i).getText().toString();
                            Players.Player p = Players.getPlayer(i);
                            Players.setName(p, name);
                        }
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

    private LinearLayout createView(Context c, LinearLayout layout, TextView tv, SelectNumberOfPlayersAdapter pa){
        final LinearLayout main_layout = layout;
        final TextView number_of_players = tv;
        final SelectNumberOfPlayersAdapter adapter = pa;

        final LinearLayout ll = new LinearLayout(c);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(params);

        final EditText et = new EditText(c);
        LayoutParams et_params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        et_params.weight = 5.0f;
        et.setLayoutParams(et_params);
        et.setInputType(InputType.TYPE_CLASS_TEXT);
        et_list.add(et);
        //et.setId(et_list.size() - 1);


        Button button = new Button(c);
        LayoutParams button_params = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        button_params.weight = 1.0f;
        button.setLayoutParams(button_params);
        Drawable top = ContextCompat.getDrawable(c, android.R.drawable.ic_delete);
        button.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = et_list.lastIndexOf(et);
                et_list.remove(index);
                Players.remove(index);
                adapter.notifyDataSetChanged();
                number_of_players.setText(Integer.toString(Players.playerCount()));
                main_layout.removeView(ll);
            }
        });


        ll.addView(et);
        ll.addView(button);

        return ll;
    }
}


