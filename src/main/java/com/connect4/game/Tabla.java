package com.connect4.game;

/* 
 * Tábla osztály:
 * Ez az osztály a tábla kezeléséért felel,
 * a lépések elhelyezésétől kezdve a győzelem ellenőrzéséig
 * és a tábla állapotának mentéséig. 
 * Felelős a játék alapvető mechanikájáért,
 * mint a lépések megtétele, és a nyertes keresése.
*/

public class Tabla {
    /*
     * Ez a kétdimenziós tömb a játék tábláját reprezentálja,
     * ahol minden elem vagy egy üres hely (' '),
     * vagy egy játékos karaktere ('S' vagy 'P'):
     */
    private char[][] tabla;
    private int sorok;
    private int oszlopok;
    private static final int OSZLOPSZAM = 6;

    /*
     * Inicializálja a tábla méretét a megadott sorokkal
     * és oszlopokkal, és üres (' ') karakterekkel tölti
     * fel a táblát.
     */
    public Tabla(int sorok, int oszlopok) {
        this.sorok = sorok;
        this.oszlopok = oszlopok;
        tabla = new char[sorok][oszlopok];
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok; j++) {
                tabla[i][j] = ' '; // üres hely
            }
        }
    }

    // Új konstruktor, amely char[][]-t fogad
    public Tabla(char[][] tabla) {
        this.sorok = tabla.length;
        this.oszlopok = tabla[0].length;
        this.tabla = new char[sorok][oszlopok];
        for (int i = 0; i < sorok; i++) {
            // Másold át a beolvasott táblát
            System.arraycopy(tabla[i], 0, this.tabla[i], 0, oszlopok);
        }
    }

    public int getMaxOszlop() {
        return OSZLOPSZAM;
    }

    /*
     * A játékos megadott oszlopába helyezi a karakterét,
     * ha az oszlop még nincs tele.
     * Azt is ellenőrizzük, hogy az oszlop létező és érvényes-e.
     */
    public boolean lehelyez(int oszlop, char jatekos) {
        if (oszlop < 0 || oszlop >= oszlopok) {
            return false; // Érvénytelen oszlop
        }
        for (int i = sorok - 1; i >= 0; i--) {
            if (tabla[i][oszlop] == ' ') {
                tabla[i][oszlop] = jatekos;
                return true; // Sikeres lehelyezés
            }
        }
        return false; // Az oszlop tele van
    }

    // Ellenőrzi, hogy a megadott játékos nyert-e.
    public boolean ellenorizGyoz(char jatekos) {
        return ellenorizGyozVizszintes(jatekos) || ellenorizGyozFuggoleges(jatekos) || ellenorizGyozAtlo(jatekos);
    }

    /*
     * A győzelmet három különböző módon
     * (vízszintes, függőleges, átlós) ellenőrzi (alább is):
     */
    private boolean ellenorizGyozVizszintes(char jatekos) {
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok - 3; j++) {
                if (tabla[i][j] == jatekos && tabla[i][j + 1] == jatekos && tabla[i][j + 2] == jatekos
                        && tabla[i][j + 3] == jatekos) {
                    return true;
                }
            }
        }
        return false;
    }

    // Függőleges győzelem ellenőrzése
    private boolean ellenorizGyozFuggoleges(char jatekos) {
        for (int i = 0; i < sorok - 3; i++) {
            for (int j = 0; j < oszlopok; j++) {
                if (tabla[i][j] == jatekos && tabla[i + 1][j] == jatekos && tabla[i + 2][j] == jatekos
                        && tabla[i + 3][j] == jatekos) {
                    return true;
                }
            }
        }
        return false;
    }

    // Átlós győzelem ellenőrzése
    private boolean ellenorizGyozAtlo(char jatekos) {
        for (int i = 0; i < sorok - 3; i++) {
            for (int j = 0; j < oszlopok - 3; j++) {
                if (tabla[i][j] == jatekos && tabla[i + 1][j + 1] == jatekos && tabla[i + 2][j + 2] == jatekos
                        && tabla[i + 3][j + 3] == jatekos) {
                    return true;
                }
            }
        }
        for (int i = 3; i < sorok; i++) {
            for (int j = 0; j < oszlopok - 3; j++) {
                if (tabla[i][j] == jatekos && tabla[i - 1][j + 1] == jatekos && tabla[i - 2][j + 2] == jatekos
                        && tabla[i - 3][j + 3] == jatekos) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Visszaadja a tábla aktuális állapotát
     * (a tabla kétdimenziós tömböt)
     */
    public char[][] getTabla() {
        return tabla;
    }

    /*
     * Megjeleníti a táblát a konzolon,
     * soronként és oszloponként.
     */
    public void kiirTabla() {
        // Kiírja a táblát
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok; j++) {
                System.out.print("|" + tabla[i][j]); // Kiírjuk a táblát
            }
            // A sor számát a tábla végén írja ki
            System.out.print("| " + i);
            // Új sor kezdése
            System.out.println();
        }

        // Kiírjuk a táblázat elválasztó vonalát
        for (int j = 0; j < oszlopok; j++) {
            // Két kötőjelet írunk minden oszlop közé
            System.out.print("--");
        }
        // Az utolsó elválasztó vonal vége
        System.out.println("-");

        // Kiírjuk az oszlopok címkéit
        for (int j = 0; j < oszlopok; j++) {
            System.out.print(" " + (char) ('a' + j)); // Kiírjuk az oszlop címkéit
        }
        // Új sor kezdése
        System.out.println();
    }

    /*
     * Visszaadja az oszlopok számát (érvényes oszlop),
     * hogy más részek is elérhessék.
     */
    public int getOszlopok() {
        return oszlopok;
    }

    // Visszaadja az oszlopok számát
    public int getOszlopokSzama() {
        return oszlopok;
    }

    // Visszaadja a sorok számát
    public boolean isValidMove(int oszlop) {
        if (oszlop < 0 || oszlop >= oszlopok) {
            return false; // Érvénytelen oszlop
        }
        return tabla[0][oszlop] == ' '; // Ellenőrzi, hogy az oszlop üres-e
    }

    public boolean isFull() {
        for (int col = 0; col < oszlopok; col++) {
            if (!isColumnFull(col)) {
                return false;
            }
        }
        return true;
    }

    private boolean isColumnFull(int col) {
        for (int row = 0; row < sorok; row++) {
            if (tabla[row][col] == ' ') { // Ha üres cella van
                return false;
            }
        }
        return true;
    }

}
