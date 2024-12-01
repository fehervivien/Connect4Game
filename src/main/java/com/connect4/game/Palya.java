package com.connect4.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/*
 * Pálya műveletek: betöltés, beolvasás, mentés
 */

 public class Palya {
    // A játék tábla
    private char[][] board;
    // A tábla sorainak és oszlopainak száma
    private final int rows = 7;
    private final int columns = 6;

    /*
     * Alapértelmezett konstruktor: Inicializálja a táblát 
     * és beállítja az alapértelmezett értékeket.
     */
    public Palya() {
        this.board = new char[rows][columns];
        initializeBoard();
    }

    /*
     * Konstruktor: Betölti a táblát egy fájlból.
     * @param filePath A fájl elérési útja, amelyből a tábla betöltésre kerül.
     */
    public Palya(String filePath) {
        this.board = new char[rows][columns];
        loadBoard(filePath);
    }

    /*
     * Inicializálja a táblát alapértelmezett értékekkel (üres mezők).
     */
    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void loadBoard(String filePath) {
        // A játéktábla alaphelyzetbe állítása a betöltés előtt
        initializeBoard(); 
        try {
            // Xml fájl létrehozása a megadott elérési úttal
            File xmlFile = new File(filePath); 

            // Dokumentumépítő gyár létrehozása (az XML feldolgozásában segít)
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
            
            // Dokumentumépítő létrehozása
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
            
            // XML fájl beolvasása és dokumentum létrehozása
            Document doc = dBuilder.parse(xmlFile); 
            
            // A dokumentum normalizálása a struktúra tisztaságáért
            doc.getDocumentElement().normalize(); 
    
            // Az összes <row> elem lekérése
            NodeList rowList = doc.getElementsByTagName("row"); 
            for (int row = 0; row < rowList.getLength(); row++) {
                Element rowElement = (Element) rowList.item(row);

                // Az aktuális sor celláinak lekérése
                NodeList cellList = rowElement.getElementsByTagName("cell");
                for (int col = 0; col < cellList.getLength(); col++) { 
                    Element cellElement = (Element) cellList.item(col);

                    // A cella szöveges tartalmának első karaktere
                    char cellValue = cellElement.getTextContent().charAt(0); 
                    // A játéktábla megfelelő cellájának értékének beállítása
                    board[row][col] = cellValue; 
                }
            }
        // Hiba esetén
        } catch (Exception e) { 
            // Hibaüzenet kiírása
            System.out.println("Hiba a fájl beolvasása közben: " + e.getMessage()); 
            // A játéktábla alaphelyzetbe állítása
            initializeBoard(); 
        }
    }
    
    //Tábla mentése
    public void saveBoard(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("<board>\n");

            for (int i = 0; i < rows; i++) {
                bw.write("  <row>\n"); 
                for (int j = 0; j < columns; j++) {
                    bw.write("    <cell>" + board[i][j] + "</cell>\n"); 
                }
                bw.write("  </row>\n");
            }

            bw.write("</board>\n");
            System.out.println("A tábla sikeresen mentésre került: " + filePath);
        } catch (IOException e) {
            System.out.println("Hiba a fájl írása közben: " + e.getMessage());
            throw new RuntimeException("Hiba történt a pálya mentésekor.", e);
        }
    }

    // Pálya visszaadása
    public char[][] getBoard() {
        return board;
    }

    // Pálya állásának frissítése az adott oszlopban
    public boolean updateBoard(int column, char symbol) {
        for (int row = rows - 1; row >= 0; row--) {
            if (board[row][column] == ' ') { // Ha az adott cella üres
                board[row][column] = symbol;
                return true;
            }
        }
        return false; // Ha az oszlop tele van
}

}
