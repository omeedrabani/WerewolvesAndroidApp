package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import java.util.ArrayList;
import java.util.List;

public class Players {
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

    public static void setRole(Player p, String role) {
        p.role = role;
        adapter.notifyDataSetChanged();
    }

    public static void kill(int pos) {
        PLAYERS.get(pos).alive = false;
        adapter.notifyDataSetChanged();
    }

    public boolean isAlive(int pos) {
        return PLAYERS.get(pos).alive;
    }

    //could be used when ending game before going back to main screen
    public static void clear() {
        PLAYERS.clear();
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

    public static class Player {
        public String name;
        public String role;
        public boolean alive;

        public Player() {
            this.alive = true;
        }
    }
}

