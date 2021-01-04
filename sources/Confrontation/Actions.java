package Confrontation;

import WorldBasics.Elements.Element;

/** Actions class package:
 * actions represents possible actions that hostiles and explorers can do
 * in battle. this includes any skill that they will use during their
 * turn in the battle system. actions will be based on the Action
 * object that will provide characteristics of all actions: a name, an 
 * elemental attribute, a category, a target and a description. categories 
 * and targets will be defined as enums.
 */
public class Actions{
    
    /** Category enum:
     * categories are two-fold: PHYSICAL and SPECIAL. This only matters to
     * the battle system as it is used to determine wheter an action will
     * use physical-related stats (ATK, DEF) or special-related stats
     * (MAGI, RES).
     */
    public static enum Category{
        PHYSICAL, SPECIAL;
    }

    /** Target enum:
     * targets define the target of an action. actions can target
     * an opponent (OPPT), an ally (ALLY) or the unit itsel (SELF) or
     * all opponents (OPPX) or all allies - itself included (ALLX).
     * this allows the battle system to know the targets of an action.
     */
    public static enum Target{
        OPPT, ALLY, SELF, OPPX, ALLX;
    }

    /** Action object
     * defines any action that can be performed in battle. it provides 
     * a name, an elemental attribute, a category, a target and a 
     * description.
     */
    public static class Action{
        // attributes
        private String name;
        private Element attribute;
        private Category catg;
        private Target target;
        private String description;
    }
}