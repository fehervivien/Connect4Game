package com.connect4.game;

import java.util.Scanner;
import com.connect4.highscore.HighScoreManager;

public class GameRunner {
    // Az InputProvider osztály példánya, 
    //amely a bemenetet kezeli.
    private final Scanner inputProvider;

    public GameRunner(Scanner scanner) {
        // Inicializálja az inputProvider-t, 
        //amelyet a játék során használunk.
        this.inputProvider = scanner;
    }

    /**
     * A játék fő logikáját kezeli.
     * Kérdezi a felhasználót, hogy szeretne-e 
     * betölteni egy játékpályát fájlból,
     * majd létrehozza a játéktáblát és elindítja
     * a játék ciklust.
     */
    public void runGame() {
        System.out.println("Szeretnél betölteni egy játékpályát egy fájlból? (Igen/Nem)");
        // Felhasználói válasz a fájl betöltésére.
        String choice = inputProvider.nextLine();

        Palya game;
        Tabla tabla;

        // Ha a felhasználó igennel válaszolt, betölti a fájlt.
        if (choice.equalsIgnoreCase("Igen")) {
            System.out.print("Add meg a fájl elérési útját: ");
            // A fájl elérési útjának bekérése.
            String filePath = inputProvider.nextLine();
            game = new Palya(filePath);
            // Játéktábla inicializálása a fájlból beolvasott állapot alapján.
            tabla = new Tabla(game.getBoard());
        } else {
            // Új játéktábla létrehozása fájl nélkül.
            game = new Palya();
            tabla = new Tabla(7, 6);
        }

        Jatekos jatekos1 = new Ember('S');
        Jatekos jatekos2 = new Gep('P');
        Jatekos jelenlegiJatekos = jatekos1;
        HighScoreManager highScoreManager = new HighScoreManager();

        // A játék ciklusa.
        while (true) {
            tabla.kiirTabla(); // Játéktábla kiírása a konzolra.
            System.out.println("Játékos " + jelenlegiJatekos.getJel() + ", válassz egy oszlopot (a-f): ");
            int oszlop;

            // Emberi játékos lépése.
            if (jelenlegiJatekos instanceof Ember) {
                // Felhasználói lépés lekérése.
                oszlop = getPlayerMove();
            } else {
                // Gépi játékos lépése.
                oszlop = jelenlegiJatekos.lepes(tabla);
                System.out.println("A gép " + oszlop + "-ba tett lépést.");
            }
            // Játék állapotának frissítése.
            game.updateBoard(oszlop, jelenlegiJatekos.getJel());

            // Ellenőrzi, hogy a lépés sikeres volt-e.
            if (tabla.lehelyez(oszlop, jelenlegiJatekos.getJel())) {
                // Játék állapotának mentése.
                game.saveBoard("Savedgame.xml");

                // Ellenőrzi, hogy a jelenlegi játékos nyert-e.
                if (tabla.ellenorizGyoz(jelenlegiJatekos.getJel())) {
                    tabla.kiirTabla();
                    System.out.println("Játékos " + jelenlegiJatekos.getJel() + " nyert!");
                    System.out.print("Add meg a neved a high score táblázathoz: ");

                    // Felhasználó neve bekérése.
                    String nev = inputProvider.nextLine();
                    // High score mentése.
                    highScoreManager.mentesHighScore(nev, 1);
                    highScoreManager.kiirHighScores();
                    break;
                }
                // A játékosok váltása.
                jelenlegiJatekos = (jelenlegiJatekos == jatekos1) ? jatekos2 : jatekos1;
            } else {
                System.out.println("Az oszlop tele van, válassz másikat!");
            }
        }
    }

    /**
     * Kéri a felhasználótól, hogy válasszon egy oszlopot.
     * Validálja a bemenetet, és biztosítja, hogy csak érvényes
     * betűket fogadjon el.
     * 
     * @return A felhasználó által választott oszlop.
     */
    private int getPlayerMove() {
        while (true) {
            String input = inputProvider.nextLine().trim().toLowerCase(); // Bevitel lekérése, kisbetűs formában

            // Ellenőrizzük, hogy a bemenet egy karakter 
            // és érvényes oszlopbetű-e
            if (input.length() == 1 && input.charAt(0) >= 'a' && input.charAt(0) < 'a' + 6) { // Feltételezzük, hogy 6
                                                                                              // oszlop van
                return input.charAt(0) - 'a'; // Átalakítjuk az oszlopbetűt oszlopszámmá
            } else {
                System.out.println("Kérlek, adj meg egy érvényes betűt (a-f): ");
            }
        }
    }
}
