package com.connect4.game;

import java.io.*;

/*
 * Pálya műveletek: betöltés, beolvasás, mentés
 */

public class Palya {

    private char[][] board;
    private final int rows = 7;
    private final int columns = 6;

    public Palya() {
        this.board = new char[rows][columns];
        initializeBoard();
    }

    public Palya(String filePath) {
        this.board = new char[rows][columns];
        loadBoard(filePath);
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /*
     * Pálya betöltése
     */
    public void loadBoard(String filePath) {
        initializeBoard(); // Reset the board to empty before loading
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
    
            while ((line = br.readLine()) != null) {
                line = line.trim(); // Trim whitespace
    
                if (line.equals("<row>")) {
                    // Sor kezdete
                    continue; // Skip the opening <row> tag
                }
    
                if (line.equals("</row>")) {
                    // Sor befejezése
                    row++; // Tovább lépünk a következő sorra
                    continue; // Skip the closing </row> tag
                }
    
                // Betölti a cellákat
                if (line.startsWith("<cell>") && line.endsWith("</cell>")) {
                    // Kinyeri a cella értékét
                    
                    char cellValue = line.charAt(6); // Az 7. karakter a cella értéke
                    for (int col = 0; col < columns; col++) {
                        // Az aktuális cellát a megfelelő oszlopba írja
                        if (col == row) {
                            board[row][col] = cellValue;
                            
                        }
                        System.out.println("Olvasott sor: " + line);
System.out.println("Betöltött cella: " + cellValue + " a(" + row + "," + col + ")");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Hiba a fájl beolvasása közben: " + e.getMessage());
            initializeBoard(); // if there is an error, start with an empty board
        }
    
    }
    

    /*
     * Pálya mentése
     */
    public void saveBoard(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // XML fájl kezdete
            bw.write("<board>\n");
    
            for (int i = 0; i < rows; i++) {
                bw.write("  <row>\n"); // Sor kezdete
                for (int j = 0; j < columns; j++) {
                    bw.write("    <cell>" + board[i][j] + "</cell>\n"); // Cellák kiírása
                }
                bw.write("  </row>\n"); // Sor vége
            }
    
            // XML fájl vége
            bw.write("</board>\n");
        } catch (IOException e) {
            System.out.println("Hiba a fájl írása közben: " + e.getMessage());
            throw new RuntimeException("Hiba történt a pálya mentésekor.", e);
        }
    }
    

    public char[][] getBoard() {
        return board;
    }

    // Pálya állásának frissítése
    public void updateBoard(int row, char symbol) {
        if (row >= 0 && row < rows) {
            // Az oszlopindexet is módosítsd, ha szükséges
            board[row][0] = symbol; // Az első oszlopba írjuk
        }
    }

}
