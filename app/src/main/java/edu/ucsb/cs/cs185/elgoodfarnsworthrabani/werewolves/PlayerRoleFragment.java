package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        dismiss_button.setVisibility(View.GONE);
        player_role_textview.setVisibility(View.GONE);

        int player_position = getArguments().getInt("player_position");

        String player_name_textview_string = "Player: " + Players.getPlayer(player_position).name;
        player_name_textview.setText(player_name_textview_string);
        // TODO: implement player roles functionality (the player role textview will then no longer be blank)
        player_role_textview.setText(Players.getPlayer(player_position).role);

        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: show player role
                yes_button.setVisibility(View.GONE);
                no_button.setVisibility(View.GONE);
                verify_identity_textview.setVisibility(View.GONE);

                player_role_textview.setVisibility(View.VISIBLE);
                dismiss_button.setVisibility(View.VISIBLE);
            }
        });

        no_button.setOnClickListener(new DismissDialogListener());
        dismiss_button.setOnClickListener(new DismissDialogListener());

        return player_role_fragment_view;
    }

    public class DismissDialogListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    }
}
