package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import java.util.ArrayList;
import java.util.List;

public class PlayerContent {
    public static PlayerAdapter adapter;
    public static final List<Player> PLAYERS = new ArrayList<Player>();

    public static void setAdapter(PlayerAdapter pa) {
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
        Player p = PLAYERS.get(pos);
        p.alive = false;
        adapter.notifyDataSetChanged();
    }

    public boolean isAlive(int pos) {
        Player p = PLAYERS.get(pos);
        if (p.alive) {
            return true;
        }
        return false;
    }

    //could be used when ending game before going back to main screen
    public static void clear() {
        PLAYERS.clear();
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

