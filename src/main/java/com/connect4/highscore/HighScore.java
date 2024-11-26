package com.connect4.highscore;

/* HighScore osztály:
- Tárolja a játékos nevét és pontszámát.
- Kínál egy toString() metódust a könnyebb kiíratáshoz.*/

public class HighScore {
    private String nev;
    private int pontszam;

    public HighScore(String nev, int pontszam) {
        this.nev = nev;
        this.pontszam = pontszam;
    }

    public String getNev() {
        return nev;
    }

    public int getPontszam() {
        return pontszam;
    }

    public void addPontszam(int pontszam) {
        this.pontszam += pontszam; // Növeli a pontszámot
    }

    @Override
    public String toString() {
        return nev + ": " + pontszam;
    }
}

