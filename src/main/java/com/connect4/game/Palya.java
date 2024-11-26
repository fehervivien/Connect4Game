package com.connect4.game;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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

    public void loadBoard(String filePath) {
        initializeBoard(); // A játéktábla alaphelyzetbe állítása a betöltés előtt
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
        } catch (Exception e) { // Hiba esetén
            System.out.println("Hiba a fájl beolvasása közben: " + e.getMessage()); // Hibaüzenet kiírása
            initializeBoard(); // A játéktábla alaphelyzetbe állítása
        }
    }
    

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
