package com.connect4.game;

import com.connect4.highscore.HighScoreManager;
import com.connect4.input.InputProvider;

/**
 * A GameRunner osztály felelős a Connect4 játék futtatásáért.
 * Ez az osztály kezeli a játék inicializálását,
 * a játékosok lépéseit, a játék állapotának frissítését
 * és a győzelem ellenőrzését.
 */
public class GameRunner {
    private InputProvider inputProvider;

    /**
     * Konstruktor: Inicializálja az inputProvider mezőt.
     * @param inputProvider Az InputProvider objektum, 
     * amely a felhasználói bemeneteket kezeli.
     */
    public GameRunner(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    /**
     * A játék fő futtatási logikája.
     * Bekéri a felhasználói bemeneteket, 
     * inicializálja a játékot, kezeli a 
     * játékosok lépéseit és ellenőrzi a győzelmet.
     */
    public void runGame() {
        System.out.println("Szeretnél betölteni egy játékpályát egy fájlból? (Igen/Nem)");
        // Bemenet olvasása: Igen vagy Nem
        String choice = inputProvider.nextLine(); 
    
        Palya game;
        Tabla tabla;
    
        if ("Igen".equalsIgnoreCase(choice)) {
            /* Ha a felhasználó "Igen"-t választ, 
            kérjük be a fájl elérési útját
            */
            System.out.print("Add meg a fájl elérési útját: ");
            String filePath = inputProvider.nextLine();
    
            // Betölti a játékot a fájlból
            game = new Palya(filePath);
            tabla = new Tabla(game.getBoard());
        } else {
            // Ha a felhasználó "Nem"-et választ, új játékot indítunk
            game = new Palya();
            tabla = new Tabla(7, 6);
        }
    
        // Játékosok inicializálása
        Jatekos jatekos_ember = new Ember('S');
        Jatekos jatekos_gep = new Gep('P');
        Jatekos jelenlegiJatekos = jatekos_ember;
        HighScoreManager highScoreManager = new HighScoreManager();
    
        // Kérjük be a játékos nevét
        System.out.print("Kérlek add meg a nevedet: ");
        String nev = inputProvider.nextLine();
    
        // Játék fő ciklusa
        boolean gameRunning = true;
        while (gameRunning) {
            tabla.kiirTabla(); // Tábla kiírása
            if (jelenlegiJatekos.getJel() == 'S') {
                System.out.println(nev + ", válassz egy oszlopot (a-f): ");
            }
    
            int oszlop;
    
            if (jelenlegiJatekos instanceof Ember) {
                oszlop = getPlayerMove(); // Emberi játékos lépése
                if (oszlop == -1) {
                    System.out.println("A játék leállt érvénytelen bemenetek miatt.");
                    break; // Kilépünk a ciklusból, ha túl sok érvénytelen bemenet érkezett
                }
            } else {
                oszlop = jelenlegiJatekos.lepes(tabla); // Gép lépése
                System.out.println("A gép " + oszlop + "-ba tett lépést.");
            }
    
            if (tabla.lehelyez(oszlop, jelenlegiJatekos.getJel())) {
                game.updateBoard(oszlop, jelenlegiJatekos.getJel());
                game.saveBoard("Savedgame.xml");
    
                if (tabla.ellenorizGyoz(jelenlegiJatekos.getJel())) {
                    tabla.kiirTabla();
                    if (jelenlegiJatekos.getJel() == 'S') {
                        System.out.println("\n" + nev + " nyert, gratulálok! :)\n");
                    } else {
                        System.out.println("\n A Gép nyert!\n");
                    }
    
                    if (jelenlegiJatekos != jatekos_gep) {
                        highScoreManager.mentesHighScore(nev, 1);
                        highScoreManager.kiirHighScores();
                    }
                    gameRunning = false; // A játék véget ér
                } else {
                    jelenlegiJatekos = (jelenlegiJatekos == jatekos_ember) ? jatekos_gep : jatekos_ember;
                }
            } else {
                System.out.println("Az oszlop tele van, válassz másikat!");
            }
        }
    }

    /**
     * Bemenet olvasása az emberi játékostól (lépések).
     * @return Az oszlop száma, ahová a játékos lépni szeretne.
     */
    private int getPlayerMove() {
        int invalidInputCount = 0;
        while (true) {
            String input = inputProvider.nextLine().trim().toLowerCase();
    
            if (input.length() == 1 && input.charAt(0) >= 'a' && input.charAt(0) < 'a' + 6) {
                return input.charAt(0) - 'a';
            } else {
                System.out.println("Kérlek, adj meg egy érvényes betűt (a-f): ");
                invalidInputCount++;
                if (invalidInputCount > 10) {
                    System.out.println("Túl sok érvénytelen bemenet! A játék leáll.");
                    // Visszatér egy érvénytelen értékkel, hogy jelezze a játék leállítását
                    return -1; 
                }
            }
        }
    }
}
