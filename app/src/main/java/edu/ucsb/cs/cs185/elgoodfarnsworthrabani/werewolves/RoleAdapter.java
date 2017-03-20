package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Paint;
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
    private boolean moderator;
    private boolean day_view;
    private boolean alive;
    private List<Players.Player> playerList;

    public RoleAdapter(Context c, boolean moderator, boolean day_view, boolean alive) {
        this.context   = c;
        this.moderator = moderator;
        this.day_view  = day_view;
        this.alive = alive;
        if (moderator) {
            if (alive) {
                this.playerList = Players.ALIVE;
            } else {
                this.playerList = Players.DEAD;
            }
        }
        else {
            this.playerList = Players.PLAYERS;
        }
    }

    public int getCount() {
        return playerList.size();
    }

    public Players.Player getItem(int pos) {
        this.notifyDataSetChanged();
        return playerList.get(pos);
    }

    public long getItemId(int pos) {
        return pos;
    }

    public View getView(int pos, View v, ViewGroup parent) {
        final int player_id = getItem(pos).id;
        Activity activity = (Activity) parent.getContext();
        final FragmentManager fragment_manager = activity.getFragmentManager();

        if (moderator) {
            v = LayoutInflater.from(context).inflate(R.layout.moderator_view_role_list_item, null);
            Button view_role_button = (Button) v.findViewById(R.id.view_role_button);
            Button kill_player_button = (Button) v.findViewById(R.id.kill_player_button);
            Button undo_kill_player_button = (Button) v.findViewById(R.id.undo_kill_player_button);
            TextView view_player_role_textview = (TextView) v.findViewById(R.id.view_player_role_textview);

            if (alive) {
                kill_player_button.setVisibility(View.VISIBLE);
                undo_kill_player_button.setVisibility(View.GONE);
            }
            else {
                kill_player_button.setVisibility(View.GONE);
                undo_kill_player_button.setVisibility(View.VISIBLE);
            }

            kill_player_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Players.kill(player_id);
                    ModeratorScreen.moderator_role_adapter_day.notifyDataSetChanged();
                    ModeratorScreen.moderator_role_adapter_day_dead.notifyDataSetChanged();
                    ModeratorScreen.moderator_role_adapter_night.notifyDataSetChanged();
                    ModeratorScreen.moderator_role_adapter_night_dead.notifyDataSetChanged();
                }
            });

            undo_kill_player_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Players.revive(player_id);
                    ModeratorScreen.moderator_role_adapter_day.notifyDataSetChanged();
                    ModeratorScreen.moderator_role_adapter_day_dead.notifyDataSetChanged();
                    ModeratorScreen.moderator_role_adapter_night.notifyDataSetChanged();
                    ModeratorScreen.moderator_role_adapter_night_dead.notifyDataSetChanged();
                }
            });

            if (day_view) {
                view_role_button.setVisibility(View.VISIBLE);
                view_player_role_textview.setVisibility(View.GONE);

                view_role_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("player_id", player_id);
                        bundle.putBoolean("moderator", true);
                        bundle.putBoolean("moderator_view_roles", true);
                        PlayerRoleFragment player_role_fragment = new PlayerRoleFragment();
                        player_role_fragment.setArguments(bundle);
                        player_role_fragment.show(fragment_manager, "Fragment");
                    }
                });
            }
            else {
                view_role_button.setVisibility(View.GONE);
                view_player_role_textview.setVisibility(View.VISIBLE);
                view_player_role_textview.setText(Players.getPlayer(player_id).role);
            }
        }
        else {
            v = LayoutInflater.from(context).inflate(R.layout.view_role_list_item, null);

            Button view_role_button = (Button) v.findViewById(R.id.view_role_button);
            view_role_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("player_id", player_id);
                    bundle.putBoolean("moderator", false);
                    PlayerRoleFragment player_role_fragment = new PlayerRoleFragment();
                    player_role_fragment.setArguments(bundle);
                    player_role_fragment.show(fragment_manager, "Fragment");
                }
            });
        }

        TextView t = (TextView)v.findViewById(R.id.view_role_player_name);
        t.setText(Players.getPlayer(player_id).name);

        return v;
    }



}
