package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by shelbyelgood on 3/15/17.
 */

public class RoleAdapter extends BaseAdapter {
    private Context context;
    //List<Players.Player> playerList = Players.PLAYERS;

    public RoleAdapter(Context c) {
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

        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.view_role_list_item, null);
        }

        TextView t = (TextView)v.findViewById(R.id.view_role_player_name);
        t.setText(Players.getPlayer(pos).name);

        return v;
    }



}
