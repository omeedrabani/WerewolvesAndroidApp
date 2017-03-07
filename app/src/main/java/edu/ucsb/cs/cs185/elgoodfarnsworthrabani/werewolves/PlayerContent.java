package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import java.util.ArrayList;
import java.util.List;

public class PlayerContent {
    //declare static adapter here
    public static final List<Player> PLAYERS = new ArrayList<Player>();

    public static void add(Player p) {
        PLAYERS.add(p);
        //adapter.notifyDataSetChanged();
    }

    public Player getPlayer(int pos) {
        return PLAYERS.get(pos);
    }

    public static void setRole(int pos, String role) {
        Player p = PLAYERS.get(pos);
        p.role = role;
        //adapter.notifyDataSetChanged();
    }

    public static void kill(int pos) {
        Player p = PLAYERS.get(pos);
        p.alive = false;
        //adapter.notifyDataSetChanged();
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
        //adapter.notifyDataSetChanged();
    }



    public static class Player {
        public String name;
        public String role;
        public boolean alive;

        //maybe remove role as parameter if role isn't known upon creation
        public Player(String name, String role) {
            this.name = name;
            this.role = role;
            this.alive = true;
        }
    }
}

