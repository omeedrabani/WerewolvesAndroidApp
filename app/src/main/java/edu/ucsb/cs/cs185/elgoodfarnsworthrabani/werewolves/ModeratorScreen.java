package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ModeratorScreen extends AppCompatActivity {
    public static RoleAdapter moderator_role_adapter_day;
    public static RoleAdapter moderator_role_adapter_day_dead;
    public static RoleAdapter moderator_role_adapter_night;
    public static RoleAdapter moderator_role_adapter_night_dead;
    public int theme;
    public int phase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        phase = intent.getIntExtra("phase", 0);
        theme = intent.getIntExtra("theme", R.style.AppTheme);
        setTheme(theme);
        setContentView(R.layout.activity_moderator_screen);

        Players.refresh_roles = false;

        // TODO: don't forget to add a "finish game" button and have that reset all the player lives and notify the adapters that data has changed

        ListView moderator_day_view_role_list   = (ListView) findViewById(R.id.moderator_day_view_role_list);
        ListView moderator_day_view_role_list_dead   = (ListView) findViewById(R.id.moderator_day_view_role_list_dead);
        ListView moderator_night_view_role_list = (ListView) findViewById(R.id.moderator_night_view_role_list);
        ListView moderator_night_view_role_list_dead   = (ListView) findViewById(R.id.moderator_night_view_role_list_dead);

        moderator_role_adapter_day = new RoleAdapter(this, true, true, true);
        moderator_role_adapter_day_dead = new RoleAdapter(this, true, true, false);
        moderator_role_adapter_night = new RoleAdapter(this, true, false, true);
        moderator_role_adapter_night_dead = new RoleAdapter(this, true, false, false);
        moderator_day_view_role_list.setAdapter(moderator_role_adapter_day);
        moderator_day_view_role_list_dead.setAdapter(moderator_role_adapter_day_dead);
        moderator_night_view_role_list.setAdapter(moderator_role_adapter_night);
        moderator_night_view_role_list_dead.setAdapter(moderator_role_adapter_night_dead);

        final LinearLayout moderator_day_view   = (LinearLayout) findViewById(R.id.moderator_day_view);
        final LinearLayout moderator_night_view = (LinearLayout) findViewById(R.id.moderator_night_view);

        if (phase==0) {
            moderator_night_view.setVisibility(View.GONE);
        }
        else {
            moderator_day_view.setVisibility(View.GONE);
        }

        Button go_to_day_phase_button   = (Button) findViewById(R.id.go_to_day_phase_button);
        Button go_to_night_phase_button = (Button) findViewById(R.id.go_to_night_phase_button);
        final Context context = this;

        go_to_day_phase_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moderator_screen_intent = new Intent(context, ModeratorScreen.class);
                moderator_screen_intent.putExtra("theme", R.style.AppTheme);
                moderator_screen_intent.putExtra("phase", 0);
                startActivity(moderator_screen_intent);
            }
        });

        go_to_night_phase_button.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moderator_screen_intent = new Intent(context, ModeratorScreen.class);
                moderator_screen_intent.putExtra("theme", android.R.style.Theme_Black_NoTitleBar);
                moderator_screen_intent.putExtra("phase", 1);
                startActivity(moderator_screen_intent);
            }
        });

        final Button day_phase_script_button   = (Button) findViewById(R.id.day_phase_script_button);
        final Button night_phase_script_button = (Button) findViewById(R.id.night_phase_script_button);

        final TextView day_phase_script_textview = (TextView) findViewById(R.id.day_phase_script_textview);
        final TextView night_phase_script_textview = (TextView) findViewById(R.id.night_phase_script_textview);

        day_phase_script_button.setBackgroundColor(0xFFFFFFFF);
        night_phase_script_button.setBackgroundColor(0xFFFFFFFF);

        day_phase_script_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day_phase_script_textview.getVisibility() == View.GONE) {
                    day_phase_script_textview.setVisibility(View.VISIBLE);
                    day_phase_script_button.setBackgroundColor(0xFFACACAC);
                }
                else {
                    day_phase_script_textview.setVisibility(View.GONE);
                    day_phase_script_button.setBackgroundColor(0xFFFFFFFF);
                }
            }
        });

        night_phase_script_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (night_phase_script_textview.getVisibility() == View.GONE) {
                    night_phase_script_textview.setVisibility(View.VISIBLE);
                    night_phase_script_button.setBackgroundColor(0xFFACACAC);
                }
                else {
                    night_phase_script_textview.setVisibility(View.GONE);
                    night_phase_script_button.setBackgroundColor(0xFFFFFFFF);
                }
            }
        });


        final Button living_players_day_button   = (Button) findViewById(R.id.living_players_day_button);
        final Button dead_players_day_button     = (Button) findViewById(R.id.dead_players_day_button);
        final Button living_players_night_button = (Button) findViewById(R.id.living_players_night_button);
        final Button dead_players_night_button   = (Button) findViewById(R.id.dead_players_night_button);

        final LinearLayout day_view_alive_players   = (LinearLayout) findViewById(R.id.day_view_alive_players);
        final LinearLayout day_view_dead_players    = (LinearLayout) findViewById(R.id.day_view_dead_players);
        final LinearLayout night_view_alive_players = (LinearLayout) findViewById(R.id.night_view_alive_players);
        final LinearLayout night_view_dead_players  = (LinearLayout) findViewById(R.id.night_view_dead_players);

        day_view_alive_players.setVisibility(View.VISIBLE);
        day_view_dead_players.setVisibility(View.GONE);
        night_view_alive_players.setVisibility(View.VISIBLE);
        night_view_dead_players.setVisibility(View.GONE);

        living_players_day_button.setBackgroundColor(0xFFACACAC);
        dead_players_day_button.setBackgroundColor(0xFFFFFFFF);

        living_players_night_button.setBackgroundColor(0xFFACACAC);
        dead_players_night_button.setBackgroundColor(0xFFFFFFFF);

        living_players_day_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                living_players_day_button.setBackgroundColor(0xFFACACAC);
                dead_players_day_button.setBackgroundColor(0xFFFFFFFF);
                day_view_alive_players.setVisibility(View.VISIBLE);
                day_view_dead_players.setVisibility(View.GONE);
            }
        });

        dead_players_day_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                living_players_day_button.setBackgroundColor(0xFFFFFFFF);
                dead_players_day_button.setBackgroundColor(0xFFACACAC);
                day_view_alive_players.setVisibility(View.GONE);
                day_view_dead_players.setVisibility(View.VISIBLE);
            }
        });

        living_players_night_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                living_players_night_button.setBackgroundColor(0xFFACACAC);
                dead_players_night_button.setBackgroundColor(0xFFFFFFFF);
                night_view_alive_players.setVisibility(View.VISIBLE);
                night_view_dead_players.setVisibility(View.GONE);
            }
        });

        dead_players_night_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                living_players_night_button.setBackgroundColor(0xFFFFFFFF);
                dead_players_night_button.setBackgroundColor(0xFFACACAC);
                night_view_alive_players.setVisibility(View.GONE);
                night_view_dead_players.setVisibility(View.VISIBLE);
            }
        });
    }

}
