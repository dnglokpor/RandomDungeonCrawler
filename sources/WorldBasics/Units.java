package WorldBasics;

import java.util.Hashtable;

import Defines.Constants;
import Defines.UndefinedKeyException;
import Defines.WrongSizeStatsArrayException;
import WorldBasics.Collectibles.Gear;
import WorldBasics.Leveling.Level;
import WorldBasics.UnitStats.StatSet;

/** Units class package
 * this source will contain the declaration of all objects associated with
 * the game units. most of the contents will be class declaration with 
 * methods that they use. classes declared here are Unit, Body and
 * Job.
 */
public class Units{
    /** Unit class
     * class of living organisms. this is a base class that will be
     * inherited by any job classes. It provides them all with a name
     * and life stats of a mob: HP, ATK, DEF, MAG, RES, SPD, AGI and LUCK.
     * Adds to those a level attribute that is an instance of 
     * rpgBasics.Leveling.Level object. it also provides a flag that can
     * quickly indicate when a unit dies.
     */
    public static class Unit{
        // internal data
        protected String name;
        protected boolean isAlive;
        protected Level level;
        protected StatSet stats;
        
        /** constructor
         * expects a name, a starting level and initial stats.
         */
        public Unit(String name, int level, int[] initStats) throws WrongSizeStatsArrayException
        {
            this.name = name;
            this.level = new Level(level);
            this.stats = new StatSet(initStats);
            this.isAlive = true; // alive by default
        }

        // getters
        /**
         * return the unit's name
         */
        public String name(){
            return this.name;
        }
        /**
         * return the unit's level
         */
        public Level level(){
            return this.level;
        }
        /**
         * return current stats of unit as a StatSet object.
         */
        public StatSet stats(){
            return this.stats;
        }
        /**
         * return the unit's current life state
         */
        public boolean isAlive(){
            return this.isAlive;
        }
    }

    /** Body class
     * this class represents an adventurer body with the 4 parts they
     * can equip gear like weapons and armor on. Each part is uniquely
     * determined by a String key and it holds a 
     * WorldBasics.Collectible.Gear objects.
     */
    public static class Body{
        // attributes
        protected Hashtable<String, Gear> slots;

        /** constructor
         * initialises the body with nothing on it; represented by null
         * values. empty slot on the body will always be null.
         * this means Body objects will overwrite the property of
         * hashtables to return null when indefined keys.
         */
        public Body(){
            String[] SLOTS = new Constants().SLOTS;
            for(int i = 0; i < SLOTS.length; i++){
                this.slots.put(SLOTS[i], null);
            }
        }

        // getters
        /**
         * return the equipment piece in the specified "slot".
         * possible "slot" values are "LHAND", "RHAND", "ARMOR" and
         * "ACCESSORY".
         * if nothing is equiped on that slot a null is returned.
         * if the slot requested doesn't exist the custom
         * defines.Constants.UndefinedKeyException is thrown.
         */
        public Gear wearAt(String slot) throws UndefinedKeyException{
            if(this.slots.containsKey(slot) == false){
                // key not found
                throw new UndefinedKeyException(slot + " is not a valid key!");
            }
            // else key was found so we return the gear
            return this.slots.get(slot);
        }
    }
}