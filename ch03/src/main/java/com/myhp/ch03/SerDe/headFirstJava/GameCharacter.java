package com.myhp.ch03.SerDe.headFirstJava;

import java.io.Serializable;

/**
 * Created on 2/23/14.
 */
public class GameCharacter extends ParentNotSerializable
        implements Serializable{
    static final long serialVersionUID = 1052424284110960213L;

    public int getPower() {
        return power;
    }

    int power;

    public String getType() {
        return type;
    }

    String type;

    public String[] getWeapons() {
        return weapons;
    }

    String[] weapons;

    public GameCharacter(int p, String t, String[] w) {
        super("GameCharacter invoked");
        power = p;
        type = t;
        weapons = w;
    }
}
