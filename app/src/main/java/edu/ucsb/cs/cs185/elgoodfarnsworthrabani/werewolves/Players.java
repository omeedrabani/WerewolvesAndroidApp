package edu.ucsb.cs.cs185.elgoodfarnsworthrabani.werewolves;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Players {
    public static final String WEREWOLF     = "Werewolf";
    public static final String INVESTIGATOR = "Investigator (Town)";
    public static final String DOCTOR       = "Doctor (Town)";
    public static final String TOWNSPERSON  = "Townsperson";

    public static int player_unique_id = 0;

    public static int number_of_werewolves;
    public static int number_of_investigators;
    public static int number_of_doctors;
    public static int number_of_townspersons;

    public static boolean refresh_roles = false;

    public static final List<Player> PLAYERS = new ArrayList<Player>();
    public static final List<Player> ALIVE = new ArrayList<Player>();
    public static final List<Player> DEAD = new ArrayList<Player>();

    public static int playerCount(){
        return PLAYERS.size();
    }

    public static Player getPlayer(int player_id){
        for (Player p : PLAYERS) {
            if (p.id == player_id)
                return p;
        }

        return null;
    }

    static Player getPlayerFromIndex(int index) {
        return PLAYERS.get(index);
    }

    public static void add(Player p) {
        PLAYERS.add(p);
        ALIVE.add(p);
    }

    public static void remove(int player_id) {
        Iterator<Player> iter = PLAYERS.iterator();
        Iterator<Player> iterAlive = ALIVE.iterator();
        Iterator<Player> iterDead = DEAD.iterator();

        while (iter.hasNext()) {
            if (iter.next().id == player_id) {
                iter.remove();
                break;
            }
        }

        while(iterAlive.hasNext()) {
            if (iterAlive.next().id == player_id) {
                iterAlive.remove();
                break;
            }
        }

        while(iterDead.hasNext()) {
            if (iterDead.next().id == player_id) {
                iterDead.remove();
                break;
            }
        }
    }


    public static void setName(int player_id, String name) {
        for (Player p : PLAYERS) {
            if (p.id == player_id) {
                PLAYERS.get(PLAYERS.indexOf(p)).name = name;
                break;
            }
        }
    }

    public static void setRole(int player_id, String role) {
        for (Player p : PLAYERS) {
            if (p.id == player_id) {
                PLAYERS.get(PLAYERS.indexOf(p)).role = role;
                break;
            }
        }
    }

    public static void setRoleFromIndex(int index, String role) {
        PLAYERS.get(index).role = role;
    }

    public static void kill(int player_id) {
        for (Player p : ALIVE) {
            if (p.id == player_id) {
                DEAD.add(p);
                ALIVE.remove(ALIVE.indexOf(p));
                break;
            }
        }
    }

    public static void revive(int player_id) {
        for (Player p : DEAD) {
            if (p.id == player_id) {
                ALIVE.add(p);
                DEAD.remove(DEAD.indexOf(p));
                break;
            }
        }
    }

    static void clearPlayerRoles() {
        for (Player p : PLAYERS) {
            p.role = null;
        }

    }

    //could be used when ending game before going back to main screen
    public static void clear() {
        ArrayList<Integer> player_ids_to_remove = new ArrayList<Integer>();
        for (Player p : PLAYERS) {
            player_ids_to_remove.add(p.id);
        }
        for (int p_id : player_ids_to_remove) {
            Players.remove(p_id);
        }
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
        public int id;

        public Player(boolean quick_add) {
            this.alive = true;
            this.name = quick_add ? (Players.player_unique_id + 1) + "" : "";
            this.id = Players.player_unique_id++;
        }
    }
}

