package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.EditText;

import java.util.List;

public class SelectNumberOfPlayersAdapter extends BaseAdapter {
    private Context context;
    //List<Players.Player> playerList = Players.PLAYERS;

    public SelectNumberOfPlayersAdapter(Context c) {
        context = c;
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
            v = LayoutInflater.from(context).inflate(R.layout.input_player, null);
            holder.name = (EditText) v.findViewById(R.id.player_name_input);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }


        holder.name.setText(Players.getPlayer(pos).name);
        holder.name.setId(pos);

        holder.name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    final int position = v.getId();
                    final EditText Caption = (EditText) v;
                    Players.getPlayer(position).name = Caption.getText().toString();
                }
            }
        });

        return v;
    }

    class ViewHolder {
        EditText name;
    }

}
