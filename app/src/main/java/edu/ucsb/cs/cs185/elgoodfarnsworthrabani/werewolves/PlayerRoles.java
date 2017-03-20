package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.Random;

public class PlayerRoles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_roles);

        int number_of_werewolves    = Players.number_of_werewolves;
        int number_of_investigators = Players.number_of_investigators;
        int number_of_doctors       = Players.number_of_doctors;
        int number_of_townspersons  = Players.number_of_townspersons;

        int player_count = Players.playerCount();
        Random r = new Random();
        int player_position;

        Button start_game_button = (Button) findViewById(R.id.start_game_button);
        final FragmentManager fragment_manager = getFragmentManager();

        start_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("player_id", 0);
                bundle.putBoolean("moderator", true);
                bundle.putBoolean("moderator_view_roles", false);
                PlayerRoleFragment player_role_fragment = new PlayerRoleFragment();
                player_role_fragment.setArguments(bundle);
                player_role_fragment.show(fragment_manager, "Fragment");
            }
        });

        if (Players.refresh_roles) {
            Players.clearPlayerRoles();

            while (number_of_werewolves > 0) {
                player_position = r.nextInt(player_count);
                if (Players.getPlayerFromIndex(player_position).role == null) {
                    Players.setRoleFromIndex(player_position, Players.WEREWOLF);
                    number_of_werewolves--;
                }
            }

            while (number_of_investigators > 0) {
                player_position = r.nextInt(player_count);
                if (Players.getPlayerFromIndex(player_position).role == null) {
                    Players.setRoleFromIndex(player_position, Players.INVESTIGATOR);
                    number_of_investigators--;
                }
            }

            while (number_of_doctors > 0) {
                player_position = r.nextInt(player_count);
                if (Players.getPlayerFromIndex(player_position).role == null) {
                    Players.setRoleFromIndex(player_position, Players.DOCTOR);
                    number_of_doctors--;
                }
            }

            while (number_of_townspersons > 0) {
                player_position = r.nextInt(player_count);
                if (Players.getPlayerFromIndex(player_position).role == null) {
                    Players.setRoleFromIndex(player_position, Players.TOWNSPERSON);
                    number_of_townspersons--;
                }
            }
        }

        final RoleAdapter roleAdapter = new RoleAdapter(this, false, false, true);
        ListView l = (ListView)findViewById(R.id.view_role_list);
        l.setAdapter(roleAdapter);
    }
}
