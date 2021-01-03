package WorldBasics;

import WorldBasics.UnitStats.StatSet;
import defines.WrongSizeStatsArrayException;

public class Collectibles {
    /*
    this source contains all the ojects that determine an obtainable and 
    stockable object in the game world.
    the "Item" class will be inherited by other types of items objects.
    */
    
    // Item class
    public static class Item{
        /*
        class that defines the basic concept of an item. it provides a name,
        a string description of the item and also an integer value. a graphic
        description of items will be provided later (image ref).
        */

        // data
        protected String name;
        protected String description;
        protected int price;

        // constructor expects a name and a description
        public Item(String name, String description, int value){
            this.name = name;
            this.description = description;
            this.price = value;
        }

        // copy constructor
        /**
         * construct a new Item that has exactly the same attributes as
         * the passed "model" Item.
         * @param model
         */
        public Item(Item model){
            this.name = model.name;
            this.description = model.description;
            this.price = model.price;
        }

        // getters
        public String getName(){
            /* returns item name */
            return this.name;
        }
        public String describe(){
            /* returns item description */
            return this.description;
        }
        public int cost(){
            /* returns item value */
            return this.price;
        }
    }
    
    // gear class
    public static class Gear extends Item{
        /*
        this class represents items that can be equipped; thus it adds to
        the normal name, description and price fields of items, a type,
        a set of stats and a slot requirement indicator.
        possible values for the type and the req attributes can be found 
        in defines.Constants.java.
        */

        // attributes
        private String type;
        private StatSet stats;
        private String req;

        // constructor
        public Gear(String name, String description, int price, String type,
            int[] initStats, String req) throws WrongSizeStatsArrayException
        {
            /**
             * expects a name, a description and a price but also an array
             * of initial stat values.
             */
            super(name, description, price); // construct item part
            // init gear attributes
            this.type = type;
            this.stats = new StatSet(initStats);
            this.req = req;
        }

        // getters
        public String type(){
            /**
             * returns the type of the gear
             */
            return this.type;
        }
        public String req(){
            /**
             * returns the type of the gear
             */
            return this.req;
        }
        public StatSet stats(){
            /**
             * returns the Statset object of the gear
             */
            return this.stats;
        }
    }    
}
