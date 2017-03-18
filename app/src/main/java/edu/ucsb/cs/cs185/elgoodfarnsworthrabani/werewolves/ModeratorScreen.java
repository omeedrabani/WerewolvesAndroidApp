package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ModeratorScreen extends AppCompatActivity {
    public static RoleAdapter moderator_role_adapter_day;
    public static RoleAdapter moderator_role_adapter_night;
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

        // TODO: change background and text colors for night phase so the visual difference is easily apparent

        ListView moderator_day_view_role_list   = (ListView) findViewById(R.id.moderator_day_view_role_list);
        ListView moderator_night_view_role_list = (ListView) findViewById(R.id.moderator_night_view_role_list);

        moderator_role_adapter_day = new RoleAdapter(this, true, true);
        moderator_role_adapter_night = new RoleAdapter(this, true, false);
        moderator_day_view_role_list.setAdapter(moderator_role_adapter_day);
        moderator_night_view_role_list.setAdapter(moderator_role_adapter_night);

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
                moderator_day_view.setVisibility(View.VISIBLE);
                moderator_night_view.setVisibility(View.GONE);
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
                moderator_day_view.setVisibility(View.GONE);
                moderator_night_view.setVisibility(View.VISIBLE);
                Intent moderator_screen_intent = new Intent(context, ModeratorScreen.class);
                moderator_screen_intent.putExtra("theme", android.R.style.Theme_Black_NoTitleBar);
                moderator_screen_intent.putExtra("phase", 1);
                startActivity(moderator_screen_intent);
            }
        });

    }

}
