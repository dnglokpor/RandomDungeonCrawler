package Confrontation;

import java.util.Arrays;

import Defines.Constants;
import Defines.WrongSizeStatsArrayException;
import WorldBasics.Collectibles.Item;
import WorldBasics.Units.*;

/** Hostiles class package
 * this source contains the definition of all the hostile mobs of the
 * dungeon. it is based on the WorldBasics.Units.Unit object (inherit)
 * and adds more attributes to make distinguished creatures.
 */
public class Hostiles {
    
    /** monster object
     * this class represents regular hostile mobs that can be
     * encountered while exploring the dungeon floors.
     * they inherits units attributes but also initialize their own
     * carried attribute.
     */
    public static class Monster extends Unit{
        // attributes
        protected Item[] carried;

        // constructor
        /**
         * expects a name, a level value, a stats set, and a list of loot items
         * to drop on death.
         */
        public Monster(String name, int level, int[] initStats,
            Item[] loot) throws WrongSizeStatsArrayException
        {
            super(name, level, initStats); // build unit
            // deep copy the loot
            this.carried = new Item[loot.length];
            for(int i =0; i < loot.length; i++){
                this.carried[i] = new Item(loot[i]); // clone each loot item
            }
        }

        // getters
        /**
         * returns the reference to the list of items the monster carries.
         * @return a WorldBasics.Collectibles.Item[3]
         */
        public Item[] carried(){
            return this.carried;
        }
    }

    /** boss object
     * this class represents a boss monster, guardian of stratum exit
     * staircases. a boss monster is distinguished from other monsters
     * by multiple forms; each form enhancing some of their stats. thus
     * they add to the monster configuration a number of forms, a list
     * of stats to enhance. the number of forms will be used as an
     * enhancement factor to simplify building data.
     */
    public static class Boss extends Monster{
        // attributes
        int forms;
        String[] statsToEnhance;

        // constructor
        /**
         * expects a name, a level value, a stats set, and a list of loot
         * items to drop on death; plus a number of forms and a string array
         * of stats to enhance. reference of stats name can be found in the
         * defines.Constants.STATS array.
         */
        public Boss(String name, int level, int[] initStats,
        Item[] loot, int forms, String[] statsList) throws 
        WrongSizeStatsArrayException
        {
            // build the monster
            super(name, level, initStats, loot);
            // add Boss attributes
            this.forms = forms;
            this.statsToEnhance = statsList.clone();
        }

        // getters
        /**
         * return the remaining number of forms
         * @return an int
         */
        public int forms(){
            return this.forms;
        }
        
        // setters
        /**
         * change to the next form. develop the stats in statsToEnhance
         * and reinit all it's stats to get rid of stat changes. also
         * reduce the number of forms remaining by 1.
         */
        public void morph(){
            // get array of all stats string identifiers
            String[] STATS = new Constants().STATS;
            for (String stat : STATS) {
                // first we check if the stat must be developped meaning
                // the stat is in the this.statsToEnhance array.
                if (Arrays.asList(this.statsToEnhance).contains(stat)){
                    // if it is then we develop that stat first by adding
                    // the remaining number of forms. this way the boss gets
                    // stronger each iteration but by a diminishing factor
                    this.stats.getStat(stat).develop(forms);
                }
                // then we reset the stat nevertheless
                this.stats.getStat(stat).reset();
            }
            this.forms--; // update remaining forms
        }
    }
}   
