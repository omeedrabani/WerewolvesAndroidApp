package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ModeratorScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moderator_screen);

        Players.refresh_roles = false;
    }
}
