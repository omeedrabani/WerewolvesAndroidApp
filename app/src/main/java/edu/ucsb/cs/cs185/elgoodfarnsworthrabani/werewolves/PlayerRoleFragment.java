package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PlayerRoleFragment extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View player_role_fragment_view = inflater.inflate(R.layout.fragment_player_role, container, false);
        getDialog().setTitle("Player Role");

        final Button yes_button     = (Button) player_role_fragment_view.findViewById(R.id.verification_yes_button);
        final Button no_button      = (Button) player_role_fragment_view.findViewById(R.id.verification_no_button);
        final Button dismiss_button = (Button) player_role_fragment_view.findViewById(R.id.dismiss_button);
        final TextView player_name_textview     = (TextView) player_role_fragment_view.findViewById(R.id.player_name_textview);
        final TextView player_role_textview     = (TextView) player_role_fragment_view.findViewById(R.id.player_role_textview);
        final TextView verify_identity_textview = (TextView) player_role_fragment_view.findViewById(R.id.verify_identity_textview);
        final ImageView player_role_imageview = (ImageView) player_role_fragment_view.findViewById(R.id.player_role_avatar);

        dismiss_button.setVisibility(View.GONE);
        player_role_textview.setVisibility(View.GONE);
        player_role_imageview.setVisibility(View.GONE);

        final int player_id = getArguments().getInt("player_id");
        boolean moderator = getArguments().getBoolean("moderator");
        boolean moderator_view_roles = getArguments().getBoolean("moderator_view_roles");

        final Context context = getContext();

        if (moderator) {
            if (moderator_view_roles) {
                String player_name_textview_string = "Player: " + Players.getPlayer(player_id).name;
                player_name_textview.setText(player_name_textview_string);
                player_role_textview.setText(Players.getPlayer(player_id).role);
                setAvatar(Players.getPlayer(player_id).role, player_role_imageview);

                yes_button.setVisibility(View.GONE);
                no_button.setVisibility(View.GONE);
                verify_identity_textview.setVisibility(View.GONE);

                player_role_textview.setVisibility(View.VISIBLE);
                dismiss_button.setVisibility(View.VISIBLE);
                player_role_imageview.setVisibility(View.VISIBLE);
            }
            else {
                String player_name_textview_string = "Moderator";
                player_name_textview.setText(player_name_textview_string);
                yes_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        Intent moderator_screen_intent = new Intent(context, ModeratorScreen.class);
                        moderator_screen_intent.putExtra("theme", R.style.AppTheme);
                        moderator_screen_intent.putExtra("phase", 0); //0=day
                        startActivity(moderator_screen_intent);
                    }
                });
            }
        }
        else {
            String player_name_textview_string = "Player: " + Players.getPlayer(player_id).name;
            player_name_textview.setText(player_name_textview_string);
            player_role_textview.setText(Players.getPlayer(player_id).role);
            setAvatar(Players.getPlayer(player_id).role, player_role_imageview);

            yes_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    yes_button.setVisibility(View.GONE);
                    no_button.setVisibility(View.GONE);
                    verify_identity_textview.setVisibility(View.GONE);

                    player_role_textview.setVisibility(View.VISIBLE);
                    dismiss_button.setVisibility(View.VISIBLE);
                    player_role_imageview.setVisibility(View.VISIBLE);
                }
            });
        }

        no_button.setOnClickListener(new DismissDialogListener());
        dismiss_button.setOnClickListener(new DismissDialogListener());

        return player_role_fragment_view;
    }

    public void setAvatar(String role, ImageView i){
        if(role == "Doctor (Town)")
            i.setImageResource(R.drawable.doctor_avatar);
        else if(role == "Investigator (Town)")
            i.setImageResource(R.drawable.investigator_avatar);
        else if(role == "Werewolf")
            i.setImageResource(R.drawable.werewolf_avatar);
        else
            i.setImageResource(R.drawable.villager_avatar);
    }

    public class DismissDialogListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    }
}
