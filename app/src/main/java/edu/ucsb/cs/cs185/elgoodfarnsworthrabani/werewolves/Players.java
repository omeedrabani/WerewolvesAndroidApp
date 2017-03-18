package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Players {
    public static final String WEREWOLF     = "Werewolf";
    public static final String INVESTIGATOR = "Investigator (Town)";
    public static final String DOCTOR       = "Doctor (Town)";
    public static final String TOWNSPERSON  = "Townsperson";

    public static int number_of_werewolves;
    public static int number_of_investigators;
    public static int number_of_doctors;
    public static int number_of_townspersons;

    public static boolean refresh_roles = false;

    public static SelectNumberOfPlayersAdapter adapter;
    public static final List<Player> PLAYERS = new ArrayList<Player>();

    public static void setAdapter(SelectNumberOfPlayersAdapter pa) {
        adapter = pa;
    }

    public static int playerCount(){
        return PLAYERS.size();
    }

    public static Player getPlayer(int pos){
        return PLAYERS.get(pos);
    }

    public static void add(Player p) {
        PLAYERS.add(p);
        adapter.notifyDataSetChanged();
    }

    public static void remove(int pos) {
        PLAYERS.remove(pos);
        adapter.notifyDataSetChanged();
    }


    public static void setName(Player p, String name) {
        p.name = name;
        adapter.notifyDataSetChanged();
    }

    public static void setRole(int pos, String role) {
        PLAYERS.get(pos).role = role;
        adapter.notifyDataSetChanged();
    }

    public static void kill(int pos) {
        PLAYERS.get(pos).alive = false;
        adapter.notifyDataSetChanged();
    }

    public boolean isAlive(int pos) {
        return PLAYERS.get(pos).alive;
    }

    static void clearPlayerRoles() {
        for (Player p : PLAYERS) {
            p.role = null;
        }

    }

    //could be used when ending game before going back to main screen
    public static void clear() {
        PLAYERS.clear();
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

    public static boolean verifyInput(){
        HashSet<String> hashSet = new HashSet<>();
        for(Player p : PLAYERS) {
            if((p.name.length() == 0) || (!hashSet.add(p.name.trim().toLowerCase()))) return false;
        }
        return true;
    }

    public static class Player {
        public String name;
        public String role;
        public boolean alive;

        public Player() {
            this.alive = true;
            this.name = "";
        }
    }
}

