package WorldBasics;

public class Leveling {
    /**
     * contains the level gauge objects that can be used to keep track of the
     * progression level of something (eg: unit level, adventurer rank).
     * it is autonomous meaning it only expects progression points input to
     * keep track of itself.
     */

    // level class
    public static class Level{
        /* this class represent the level of something. a level has
        a current value and a progression to next level. progression is 
        centesimal meaning every 100 progression point the level is 
        raised by a unit automatically. designed for inheritance. */

        // data
        protected int level;
        protected int progression;

        // constructor expect a current value.
        public Level(int initValue){
            this.level = initValue;
            // update progression based on current
            this.progression = 0 + ((initValue - 1)  * 100);
        }

        // getter
        public int current(){
            /* return the current value of the level. */
            return this.level;
        }
        public int currentProg(){
            /* return the current amount of progression. */
            return this.progression;
        }

        // setters
        public boolean earn(int amount){
            /* adds "amount" of progression points to the current "progression".
            update level if needed and return a true if there was a 
            level up. */
            this.progression += amount; // update progression
            int newLevel = (this.progression % 100 + 1);
            boolean levelUp = newLevel > this.level;
            if (levelUp){
                this.level = newLevel;
            }
            return levelUp; 
        }
    }
}
