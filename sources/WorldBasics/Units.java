package WorldBasics;

import java.util.Hashtable;

import WorldBasics.Collectibles.Gear;
import WorldBasics.Collectibles.Item;
import WorldBasics.Leveling.Level;
import WorldBasics.UnitStats.StatSet;
import defines.WrongSizeStatsArrayException;
import defines.Constants;
import defines.UndefinedKeyException;

public class Units{
    /*
    this source will contain the declaration of all objects associated with
    the game units.
    most of the contents will be class declaration with methods that they use.
    classes declared here are "unit", "body", "skill", "swordman", "ranger",
    "caster", "monster", "archmonster" and "boss".
    Also contains the constant array definition of rpg elements.
    */

    // unit class
    public static class Unit{
        /*
        class of living organisms. this is a base class that will be
        inherited by any job classes. It provides them all with a name
        and life stats of a mob: HP, ATK, DEF, MAG, RES, SPD, AGI and LUCK.
        Adds to those a level attribute that is an instance of 
        rpgBasics.Leveling.Level object. it also provides a flag that can
        quickly indicate when a unit dies.
        */

        // internal data
        protected String name;
        protected boolean isAlive;
        protected Level level;
        protected StatSet stats;
        
        // constructor
        public Unit(String name, int level, int[] initStats) throws WrongSizeStatsArrayException
        {
            /**
             * expects a name, a starting level and initial stats.
             */
            this.name = name;
            this.level = new Level(level);
            this.stats = new StatSet(initStats);
            this.isAlive = true; // alive by default
        }

        // getters
        public String name(){
            /**
             * return the unit's name
             */
            return this.name;
        }
        public Level level(){
            /**
             * return the unit's level
             */
            return this.level;
        }
        public StatSet stats(){
            /**
             * return current stats of unit as a StatSet object.
             */
            return this.stats;
        }
        public boolean isAlive(){
            /**
             * return the unit's current life state
             */
            return this.isAlive;
        }
    }

    // body class
    public static class Body{
        /*
        this class represents an adventurer body with the 4 parts they
        can equip gear like weapons and armor on. Each part is uniquely
        determined by a String key and it holds a 
        WorldBasics.Collectible.Gear objects.
        */

        // attributes
        protected Hashtable<String, Gear> slots;

        // constructor
        public Body(){
            /**
             * initialises the body with nothing on it; represented by null
             * values. empty slot on the body will always be null.
             * this means Body objects will overwrite the property of
             * hashtables to return null when indefined keys.
             */
            String[] SLOTS = new Constants().SLOTS;
            for(int i = 0; i < SLOTS.length; i++){
                this.slots.put(SLOTS[i], null);
            }
        }

        // getters
        public Gear wearAt(String slot) throws UndefinedKeyException{
            /**
             * return the equipment piece in the specified "slot".
             * possible "slot" values are "LHAND", "RHAND", "ARMOR" and
             * "ACCESSORY".
             * if nothing is equiped on that slot a null is returned.
             * if the slot requested doesn't exist the custom
             * defines.Constants.UndefinedKeyException is thrown.
             */

            if(this.slots.containsKey(slot) == false){
                // key not found
                throw new UndefinedKeyException(slot + " is not a valid key!");
            }
            // else key was found so we return the gear
            return this.slots.get(slot);
        }
    }

    // monster class
    /**
     * this class represents regular hostile mobs that can be
     * encountered while exploring the dungeon floors.
     * they inherits units attributes but also initialize their own
     * carried attribute.
     */
    public static class Monster extends Unit{
        // attributes
        Item[] carried;

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
         * @return
         */
        public Item[] carried(){
            return this.carried;
        }
    }
}