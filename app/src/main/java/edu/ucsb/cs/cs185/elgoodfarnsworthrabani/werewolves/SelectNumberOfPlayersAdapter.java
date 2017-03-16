package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class SelectNumberOfPlayersAdapter extends BaseAdapter {
    //List<Players.Player> playerList = Players.PLAYERS;

    public SelectNumberOfPlayersAdapter() {
        // STUB for now
    }

    public int getCount() {
        return Players.playerCount();
    }

    public Players.Player getItem(int pos) {
        return Players.getPlayer(pos);
    }

    public long getItemId(int pos) {
        return pos;
    }

    public View getView(int pos, View v, ViewGroup parent) {
        ViewHolder holder;
        if (v == null) {
            holder = new ViewHolder();
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.input_player, parent, false);
            holder.name = (EditText) v.findViewById(R.id.player_name_input);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.name.setText(Players.getPlayer(pos).name);
        holder.name.setId(pos);

        final int player_position = pos;
        Button delete_player_button = (Button) v.findViewById(R.id.delete_player_button);
        delete_player_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Players.remove(player_position);
                SelectNumberOfPlayers.number_of_players_textview.setText(Integer.toString(Players.playerCount()));
            }
        });

        holder.name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    final int position = v.getId();
                    if (Players.playerCount() > position) {
                        final EditText Caption = (EditText) v;
                        Players.getPlayer(position).name = Caption.getText().toString();
                    }
                }
            }
        });

        return v;
    }

    class ViewHolder {
        EditText name;
    }

}
