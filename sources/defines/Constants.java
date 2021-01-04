package Defines;
public class Constants {
    /*
    the goal of this source is to store inter source constants that will be
    used overal.
    yeah its pretty much just a header file with useful reusable information.
    */

    // array of strings describing all unit stats
    public final String[] STATS = { 
        "HP", "ATK", "DEF", "MAGI", "RES", "SPD", "LUCK"
    };
    
    // initial values of units stats
    public final int[] ADV_BASE_STATS = {40, 10, 8, 10, 8, 9, 5};
    public final int[] MONSTER_BASE_STATS = {40, 10, 8, 10, 8, 9, 5};

    /* GEAR */
    // gear types
    public final String[] WEAPON_TYPES = { 
        "knife", "sword", "spear", "bow","wand", "catalyst"
    };
    public final String[] ARMOR_TYPES = { 
        "shield", "heavy", "light", "robe"
    };
    public final String[] ACCESSORY_TYPES = { 
        "ring", "bracelet", "amulet"
    };
    
    // gear slot requirements
    public final String[] GEAR_REQS = {
        "1-Hand", "2-Hand", "body", "accessory"
    };

    // body equipment slots
    public final String[] SLOTS = { "LHAND", "RHAND", "ARMOR", "ACCESSORY" };
}
