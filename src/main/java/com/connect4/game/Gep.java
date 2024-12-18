package com.connect4.game;

import java.util.Random;

/*
 * A Gep osztály egy gépi játékost reprezentál a Connect4 játékban.
 * Ez az osztály a Jatekos osztályból öröklődik, és véletlenszerűen
 * választ egy oszlopot a tábla méretén belül.
 */

// A Jatekos osztályból öröklődik
public class Gep extends Jatekos {
    private Random random;

    /*
     * Konstruktor: Meghívja a szülő
     * konstruktorát a karakter beállításához.
     */
    public Gep(char jel) {
        super(jel);
        // Inicializáljuk a Random objektumot
        random = new Random();
    }

    @Override
    /*
     * A gépi játékos véletlenszerűen választ
     * egy oszlopot a tábla méretén belül.
     * 
     * A Random osztályt használja, hogy
     * kiválasszon egy érvényes oszlopot
     * (amit a tabla.getOszlopok() ad meg).
     */

    public int lepes(Tabla tabla) {
        Random random = new Random();
        int oszlop;
        /*
         * A ciklus biztosítja, hogy a kiválasztott
         * oszlop érvényes legyen:
         */
        do {
            oszlop = random.nextInt(tabla.getOszlopokSzama()); 
        } while (!tabla.isValidMove(oszlop));
        return oszlop;
    }

}
