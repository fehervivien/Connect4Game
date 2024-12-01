package com.connect4.game;

/*
 * A Jatekos osztály egy játékost reprezentál a Connect4 játékban.
 * Ez az osztály absztrakt, és minden játékosnak egyedi lépési
 * logikát kell megvalósítania.
 */

public abstract class Jatekos {
    /*
     * A játékos jelölése ('X' vagy 'O'), amelyet
     * a tábla megjelenít.
     */
    protected char jel;

    /*
     * A konstruktor beállítja a játékos
     * karakterét
     */
    public Jatekos(char jel) {
        this.jel = jel;
    }

    /*
     * Minden játékos számára egyedi lesz,
     * és felelős a lépésért. Mivel absztrakt,
     * minden alosztályban implementálni kell.
     */
    public abstract int lepes(Tabla tabla);

    // Visszaadja a játékos karakterét.
    public char getJel() {
        return jel;
    }
}
