package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class PlayerRoles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_roles);

        final RoleAdapter roleAdapter = new RoleAdapter(this);
        ListView l = (ListView)findViewById(R.id.view_role_list);
        l.setAdapter(roleAdapter);

    }
}
