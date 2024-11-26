/* Az Ember osztály egy emberi játékost 
reprezentál. 

A Scanner objektum segítségével beolvassa 
a felhasználói bemeneteket a konzolról, 
és a *lepes metódusban* kéri a felhasználót, 
hogy válasszon egy oszlopot, ahová a következő 
lépést szeretné tenni. 

Az osztály a Jatekos osztályból származik, 
és annak metódusait és mezőit örökli.*/


package com.connect4.game;
import java.util.Scanner;


public class Ember extends Jatekos {
    // Beolvasás a felhasználótól
    private Scanner scanner;

    /*
     * Konstruktor: Meghívja a szülő
     * konstruktorát, hogy beállítsa a karaktert.
     */
    public Ember(char jel) {
        super(jel);
        /* Inicializáljuk a Scanner objektumot */
        scanner = new Scanner(System.in);
    }

    @Override
    public int lepes(Tabla tabla) {
        int oszlop = -1;
        boolean ervenyesLepes = false;

        /*
         * Ismétlődően kérje a felhasználótól
         * az oszlopot, amíg nem kap érvényes
         * lépést, ha nem jó oszlopot mond akkor
         * hibaüzenetet ad vissza
         */
        while (!ervenyesLepes) {
            System.out
                    .print("Játékos '" + jel + "', add meg az oszlop számát (0 - " + (tabla.getOszlopok() - 1) + "): ");
            oszlop = scanner.nextInt();

            if (oszlop >= 0 && oszlop < tabla.getOszlopok()) {
                ervenyesLepes = true;
            } else {
                System.out.println("Érvénytelen oszlop! Próbáld újra.");
            }
        }

        return oszlop;
    }

}
