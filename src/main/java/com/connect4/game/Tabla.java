package com.connect4.game;

/* 
 * Tábla osztály:
 * Ez az osztály a tábla kezeléséért felel,
 * a lépések elhelyezésétől kezdve a győzelem ellenőrzéséig
 * és a tábla állapotának mentéséig. Felelős a játék alapvető mechanikájáért,
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
        return OSZLOPSZAM; // Ellenőrizd, hogy ez a szám mindig pozitív
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

    /* Ellenőrzi, hogy a megadott játékos nyert-e. */
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

    /*Visszaadja a tábla aktuális állapotát 
    (a tabla kétdimenziós tömböt)*/
    public char[][] getTabla() {
        return tabla;
    }
    

    /*
     * Megjeleníti a táblát a konzolon,
     * soronként és oszloponként.
     */
    public void kiirTabla() {
        // Kiírjuk a táblát
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok; j++) {
                System.out.print("|" + tabla[i][j]);  // Kiírjuk a táblát
            }
            System.out.print("| " + i);  // A sor számát a tábla végén írjuk ki
            System.out.println();  // Új sor kezdése
        }
    
        // Kiírjuk a táblázat elválasztó vonalát
        for (int j = 0; j < oszlopok; j++) {
            System.out.print("--");  // Két kötőjelet írunk minden oszlop közé
        }
        System.out.println("-");  // Az utolsó elválasztó vonal vége
    
        // Kiírjuk az oszlopok címkéit
        //System.out.print("  ");  // Két üres hely a sorok számai előtt
        for (int j = 0; j < oszlopok; j++) {
            System.out.print(" " + (char) ('a' + j));  // Kiírjuk az oszlop címkéit
        }
        System.out.println();  // Új sor kezdése
    }
    

    /*
     * Visszaadja az oszlopok számát (érvényes oszlop),
     * hogy más részek is elérhessék.
     */
    public int getOszlopok() {
        return oszlopok;
    }

    public int getOszlopokSzama() {
        return oszlopok; // Visszatérünk az oszlopok számával
    }

    public boolean isValidMove(int oszlop) {
        if (oszlop < 0 || oszlop >= oszlopok) {
            return false; // Érvénytelen oszlop
        }
        return tabla[0][oszlop] == ' '; // Ellenőrizzük, hogy az oszlop üres-e
    }
}
