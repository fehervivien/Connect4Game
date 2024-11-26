package com.connect4.game;

import com.connect4.input.InputProvider;
import com.connect4.highscore.HighScoreManager;

public class GameRunner {
    private InputProvider inputProvider;

    public GameRunner(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    public void runGame() {
        System.out.println("Szeretnél betölteni egy játékpályát egy fájlból? (Igen/Nem)");
        String choice = inputProvider.nextLine(); // Nem Scanner-t használunk, hanem az új InputProvider-t

        Palya game;
        Tabla tabla;

        if ("Igen".equalsIgnoreCase(choice)) {
            System.out.print("Add meg a fájl elérési útját: ");
            String filePath = inputProvider.nextLine();

            game = new Palya(filePath);
            tabla = new Tabla(game.getBoard());
        } else {
            game = new Palya();
            tabla = new Tabla(7, 6);
        }

        Jatekos jatekos_ember = new Ember('S');
        Jatekos jatekos_gep = new Gep('P');
        Jatekos jelenlegiJatekos = jatekos_ember;
        HighScoreManager highScoreManager = new HighScoreManager();

        System.out.print("Kérlek add meg a nevedet: ");
        String nev = inputProvider.nextLine();

        while (true) {
            tabla.kiirTabla();
            if (jelenlegiJatekos.getJel() == 'S') {
                System.out.println(nev + ", válassz egy oszlopot (a-f): ");
            }

            int oszlop;

            if (jelenlegiJatekos instanceof Ember) {
                oszlop = getPlayerMove();
            } else {
                oszlop = jelenlegiJatekos.lepes(tabla);
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
                    break;
                }
                jelenlegiJatekos = (jelenlegiJatekos == jatekos_ember) ? jatekos_gep : jatekos_ember;
            } else {
                System.out.println("Az oszlop tele van, válassz másikat!");
            }
        }
    }

    private int getPlayerMove() {
        while (true) {
            String input = inputProvider.nextLine().trim().toLowerCase();

            if (input.length() == 1 && input.charAt(0) >= 'a' && input.charAt(0) < 'a' + 6) {
                return input.charAt(0) - 'a';
            } else {
                System.out.println("Kérlek, adj meg egy érvényes betűt (a-f): ");
            }
        }
    }
}