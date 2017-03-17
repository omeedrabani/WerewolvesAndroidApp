package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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

        Activity activity = (Activity) parent.getContext();
        final FragmentManager fragment_manager = activity.getFragmentManager();

        final int player_position = pos;

        Button view_role_button = (Button) v.findViewById(R.id.view_role_button);
        view_role_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("player_position", player_position);
                PlayerRoleFragment player_role_fragment = new PlayerRoleFragment();
                player_role_fragment.setArguments(bundle);
                player_role_fragment.show(fragment_manager, "Fragment");
            }
        });

        TextView t = (TextView)v.findViewById(R.id.view_role_player_name);
        t.setText(Players.getPlayer(pos).name);

        return v;
    }



}
