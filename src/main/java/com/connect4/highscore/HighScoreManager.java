package com.connect4.highscore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* 
* HighScoreManager osztály:
    * Kezeli a high score táblázat betöltését és mentését fájlba.
    * Képes kiírni minden játékos pontszámát.
*/

public class HighScoreManager {
    // A pontszámokat tároló lista
    private List<HighScore> highScores;

    // A fájl neve (adatbázis), ahol a pontszámok tárolva vannak
    private static final String FILE_NAME = "highscores.txt";

    // Konstruktor
    public HighScoreManager() {
        highScores = new ArrayList<>();
        betoltHighScores();
    }

    // Betölti a high score fájlt
    public void betoltHighScores() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                String nev = parts[0];
                int pontszam = Integer.parseInt(parts[1].trim());
                highScores.add(new HighScore(nev, pontszam));
            }
        } catch (IOException e) {
            System.out.println("Hiba a high score fájl betöltésekor: " + e.getMessage());
        }
    }

    // Mentés a high score fájlba
    public void mentesHighScore(String nev, int pontszam) {
        
        // Ellenőrzi, hogy a játékos már létezik-e
        HighScore existingScore = findHighScoreByName(nev);
        if (existingScore != null) {
            // Ha létezik, akkor hozzáadja a pontszámát
            existingScore.addPontszam(pontszam);
        } else {
            // Ha nem létezik, új high score objektumot hoz létre
            highScores.add(new HighScore(nev, pontszam));
        }

        // Csökkenő sorrend: a pontszámok szerint rendezi
        highScores.sort((a, b) -> Integer.compare(b.getPontszam(), a.getPontszam()));

        // Fájlba mentés
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (HighScore hs : highScores) {
                bw.write(hs.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Hiba a high score fájl mentésekor: " + e.getMessage());
        }
    }

    // Játékos keresése a nev alapján
    public HighScore findHighScoreByName(String nev) {
        for (HighScore hs : highScores) {
            // összehasonlítja a neveket
            if (hs.getNev().equalsIgnoreCase(nev)) {
                return hs; // Visszatér a megtalált high score objektummal
            }
        }
        return null; // Nem található játékos
    }

    // Kiírja a high score táblázatot
    public void kiirHighScores() {
        System.out.printf("%-20s %10s%n", "Név", "Pontszám");
        System.out.println("-------------------------------");

        for (HighScore hs : highScores) {
            System.out.printf("%-20s %10d%n", hs.getNev(), hs.getPontszam());
        }
    }

    // Getter a highScores listához
    public List<HighScore> getHighScores() {
        return highScores;
    }
}
