package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        Button new_game_button = (Button) findViewById(R.id.new_game_button);
        new_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent new_game_intent = new Intent(context, SelectNumberOfPlayers.class);
                startActivity(new_game_intent);
            }
        });

        Button rules_button = (Button) findViewById(R.id.rules_button);
        rules_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rules_intent = new Intent(context, RulesScreen.class);
                startActivity(rules_intent);
            }
        });

        Button about_us_button = (Button) findViewById(R.id.about_us_button);
        about_us_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent about_us_intent = new Intent(context, AboutUsScreen.class);
                startActivity(about_us_intent);
            }
        });
    }
}
