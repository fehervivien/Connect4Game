import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.connect4.game.Palya;

class PalyaTest {

    private Palya palya;

    @BeforeEach
    void setUp() {
        // Új játéktábla létrehozása üres állapotban
        palya = new Palya(); 
    }

    @Test
    void testInitializeBoard() {
        // Ellenőrzi, hogy a tábla üres-e
        char[][] board = palya.getBoard();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(' ', board[i][j], "A tábla cellája nem üres: (" + i + "," + j + ")");
            }
        }
    }

    @Test
    void testUpdateBoard() {
        // Ellenőrzi, hogy a tábla frissítése megfelelően működik
        // S betűvel való frissítés az 0-ás oszlopban
        assertTrue(palya.updateBoard(0, 'S')); 
        // Az alsó cellának S-nek kell lennie
        assertEquals('S', palya.getBoard()[6][0]);

        // Feltölti az oszlopot
        for (int i = 1; i < 7; i++) {
            // P betűvel frissíti az oszlopot
            palya.updateBoard(0, 'P'); 
        }
        assertFalse(palya.updateBoard(0, 'S'));
    }

    @Test
    void testLoadBoardValidFile() throws IOException {
        // Teszt fájl létrehozása érvényes tartalommal
        File testFile = new File("testBoard.xml");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("<board>\n");
            writer.write("  <row>\n");
            writer.write("    <cell>S</cell>\n");
            writer.write("    <cell> </cell>\n");
            writer.write("    <cell>P</cell>\n");
            writer.write("  </row>\n");
            writer.write("  <row>\n");
            writer.write("    <cell>P</cell>\n");
            writer.write("    <cell>S</cell>\n");
            writer.write("    <cell> </cell>\n");
            writer.write("  </row>\n");
            writer.write("</board>\n");
        }

        // Új Palya példány létrehozása és fájl betöltése
        Palya palyaToLoad = new Palya("testBoard.xml");
        char[][] board = palyaToLoad.getBoard();

        // Ellenőrzi a betöltött tábla tartalmát
        assertEquals('S', board[0][0]);
        assertEquals(' ', board[0][1]);
        assertEquals('P', board[0][2]);
        assertEquals('P', board[1][0]);
        assertEquals('S', board[1][1]);
        assertEquals(' ', board[1][2]);

        // Törli a teszt fájlt
        testFile.delete();
    }

    @Test
    void testLoadBoardInvalidFile() {
        // Ellenőrzi, hogy hiba történik-e, ha érvénytelen fájlt próbálunk betölteni
        Palya palyaToLoad = new Palya("invalidFile.xml");
        char[][] board = palyaToLoad.getBoard(); 

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(' ', board[i][j], "A tábla cellája nem üres: (" + i + "," + j + ")");
            }
        }
    }
}
