package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ModeratorScreen extends AppCompatActivity {
    public static RoleAdapter moderator_role_adapter_day;
    public static RoleAdapter moderator_role_adapter_night;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moderator_screen);

        Players.refresh_roles = false;

        // TODO: change background and text colors for night phase so the visual difference is easily apparent

        ListView moderator_day_view_role_list   = (ListView) findViewById(R.id.moderator_day_view_role_list);
        ListView moderator_night_view_role_list = (ListView) findViewById(R.id.moderator_night_view_role_list);

        moderator_role_adapter_day   = new RoleAdapter(this, true, true);
        moderator_role_adapter_night = new RoleAdapter(this, true, false);
        moderator_day_view_role_list.setAdapter(moderator_role_adapter_day);
        moderator_night_view_role_list.setAdapter(moderator_role_adapter_night);

        final LinearLayout moderator_day_view   = (LinearLayout) findViewById(R.id.moderator_day_view);
        final LinearLayout moderator_night_view = (LinearLayout) findViewById(R.id.moderator_night_view);

        moderator_night_view.setVisibility(View.GONE);

        Button go_to_day_phase_button   = (Button) findViewById(R.id.go_to_day_phase_button);
        Button go_to_night_phase_button = (Button) findViewById(R.id.go_to_night_phase_button);

        go_to_day_phase_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moderator_day_view.setVisibility(View.VISIBLE);
                moderator_night_view.setVisibility(View.GONE);
            }
        });

        go_to_night_phase_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moderator_day_view.setVisibility(View.GONE);
                moderator_night_view.setVisibility(View.VISIBLE);
            }
        });

    }
}
