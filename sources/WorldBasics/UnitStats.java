package WorldBasics;

import java.util.Hashtable;

import Defines.Constants;
import Defines.WrongSizeStatsArrayException;

public class UnitStats {

    // stat class
    public static class Stat{
        /*
        class that represent a single stat of a unit. this allows each
        stat to be its own entity thus enabling easy modification of it.
        it's purpose is to be used not inherited.
        */

        // data
        private Hashtable<String, Integer> value;
        // constructor gets passed initial value
        public Stat(int init_value){
            this.value.put("max", init_value); // max value
            this.value.put("cur", init_value); // current value
        }

        // getter
        public int current(){
            /* return current value of stat. */
            return this.value.get("cur");
        }

        public int max(){
            /* return max value of stat. */
            return this.value.get("max");
        }

        // setters
        /**
         * raise the value of both max and current values of the stat 
         * to new values. May be used for level up evolution.
         * @param increase
         */
        public void develop(int increase){
            int temp;
            temp = this.max(); // temp record max
            this.value.replace("max", temp, temp + increase);
            temp = this.current(); // temp record max
            this.value.replace("max", temp, temp + increase);
        }
        /**
         * reduce the current value of stat by the "amount". if "amount" 
         * is higher than the current value, current becomes "0". 
         * "amount" must be positif.
         */
        public void lowerBy(int amount){
            if(amount > 0){
                int temp = this.current();
                if(temp > amount)
                    this.value.replace("cur", temp, temp - amount);
                else // to avoid negative current values
                    this.value.replace("cur", temp, 0);
            }
        }
        /** raise the current value of stat by the "amount".
         * "amount" must be positif.
         */
        public void raiseBy(int amount){
            if(amount > 0){
                int old = this.current();
                // the current can go over the max
                this.value.replace("cur", old, old + amount);
            }
        }
        /**
         * reset the current value of the stat to its max value. this is
         * done by setting the current equal to the max.
         */
        public void reset(){
            int old = this.current();
            this.value.replace("cur", old, this.max());
        }
    }

    public static class StatSet{
        /**
         * this class represent the complete set of stats that any unit
         * of the game world must possess; including equipment pieces
         * to be able to interact to the battle engine. It uses the
         * "Stat" object defined earlier as it is just a
         * HashTable<String, "Stat">.
         * the complete stat set contains the 7 stat characteristics
         * that this rpg-like features: HP, ATK, DEF, MAGI, RES, SPD and
         * LUCK.
         */

        // attributes
        private Hashtable<String, Stat> set;

        // constructor requires an int array with initial values for all
        // stats defined in defines.Constants.STATS.
        public StatSet(int[] initValues) throws WrongSizeStatsArrayException{
            String[] STATS = new Constants().STATS;
            set = new Hashtable<String, Stat>(); // construct the set
            // create the stats set while making sure the "initValues" is
            // the right size
            try{
                for(int i = 0; i < STATS.length; i++){
                    this.set.put(STATS[i], new Stat(initValues[i]));
                }
            }catch (ArrayIndexOutOfBoundsException err){
                throw new WrongSizeStatsArrayException(
                    "passed initial stats array should be " + STATS.length + 
                    " long.", err);
            }
        }

        // getters
        /**
         * returns the set of stats StatSet object.
         */
        public Hashtable<String, Stat> getSet(){
            return this.set;
        }
        /**
         * returns the Stat specified by stat.
         */
        public Stat getStat(String stat){
            return this.set.get(stat);
        }

        // setters
        /**
         * reset all the stats of the set to their max value. this can be
         * useful for example when healing the user at an inn.
         */
        public void resetAll(){
            for(String stat : new Constants().STATS){
                this.getStat(stat).reset();
            }
        }
        /**
         * reset all the stats of the set but the "HP". This can be used
         * at the end of a fight to remove buffs and nerfs that way
         * the stats are at their normal values for next fight.
         */
        public void cleanseAll(){
            for(String stat : new Constants().STATS){
                if (!stat.equals("HP")) // skip the "HP" reset
                    this.getStat(stat).reset();
            }
        }
    }
}
