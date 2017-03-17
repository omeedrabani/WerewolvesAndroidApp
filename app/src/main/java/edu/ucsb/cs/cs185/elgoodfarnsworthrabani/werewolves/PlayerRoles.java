package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Random;

public class PlayerRoles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_roles);

        int number_of_werewolves    = getIntent().getExtras().getInt("number_of_werewolves");
        int number_of_investigators = getIntent().getExtras().getInt("number_of_investigators");
        int number_of_doctors       = getIntent().getExtras().getInt("number_of_doctors");
        int number_of_townspersons  = getIntent().getExtras().getInt("number_of_townspersons");

        int player_count = Players.playerCount();
        Random r = new Random();
        int player_position;

        Players.clearPlayerRoles();

        while (number_of_werewolves > 0) {
            player_position = r.nextInt(player_count);
            if (Players.getPlayer(player_position).role == null) {
                Players.setRole(player_position, Players.WEREWOLF);
                number_of_werewolves--;
            }
        }

        while (number_of_investigators > 0) {
            player_position = r.nextInt(player_count);
            if (Players.getPlayer(player_position).role == null) {
                Players.setRole(player_position, Players.INVESTIGATOR);
                number_of_investigators--;
            }
        }

        while (number_of_doctors > 0) {
            player_position = r.nextInt(player_count);
            if (Players.getPlayer(player_position).role == null) {
                Players.setRole(player_position, Players.DOCTOR);
                number_of_doctors--;
            }
        }

        while (number_of_townspersons > 0) {
            player_position = r.nextInt(player_count);
            if (Players.getPlayer(player_position).role == null) {
                Players.setRole(player_position, Players.TOWNSPERSON);
                number_of_townspersons--;
            }
        }

        final RoleAdapter roleAdapter = new RoleAdapter(this);
        ListView l = (ListView)findViewById(R.id.view_role_list);
        l.setAdapter(roleAdapter);
    }
}
