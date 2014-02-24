package com.myhp.ch03.SerDe.headFirstJava;

import java.io.Serializable;

/**
 * Created on 2/23/14.
 */
public class GameCharacter implements Serializable{
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
        power = p;
        type = t;
        weapons = w;
    }
}
