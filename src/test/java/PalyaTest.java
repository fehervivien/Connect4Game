import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.connect4.game.Palya;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PalyaTest {

    private Palya palya;

    @BeforeEach
    void setUp() {
        palya = new Palya(); // Új játéktábla létrehozása üres állapotban
    }

    @Test
    void testInitializeBoard() {
        // Ellenőrizzük, hogy a tábla üres-e
        char[][] board = palya.getBoard();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(' ', board[i][j], "A tábla cellája nem üres: (" + i + "," + j + ")");
            }
        }
    }

    @Test
    void testUpdateBoard() {
        // Ellenőrizzük, hogy a tábla frissítése megfelelően működik
        assertTrue(palya.updateBoard(0, 'S')); // S betűvel való frissítés az 0-ás oszlopban
        assertEquals('S', palya.getBoard()[6][0]); // Az alsó cellának S-nek kell lennie

        // Töltsük fel az oszlopot
        for (int i = 1; i < 7; i++) {
            palya.updateBoard(0, 'P'); // P betűvel frissítjük
        }
        assertFalse(palya.updateBoard(0, 'S')); // Az oszlop már tele van, így false-t kell visszaadnia
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

        // Ellenőrizzük a betöltött tábla tartalmát
        assertEquals('S', board[0][0]);
        assertEquals(' ', board[0][1]);
        assertEquals('P', board[0][2]);
        assertEquals('P', board[1][0]);
        assertEquals('S', board[1][1]);
        assertEquals(' ', board[1][2]);

        // Töröljük a teszt fájlt
        testFile.delete();
    }

    @Test
    void testLoadBoardInvalidFile() {
        // Ellenőrizzük, hogy hiba történik-e, ha érvénytelen fájlt próbálunk betölteni
        Palya palyaToLoad = new Palya("invalidFile.xml");
        char[][] board = palyaToLoad.getBoard(); // Alapértelmezetten üres tábla kell, hogy legyen

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(' ', board[i][j], "A tábla cellája nem üres: (" + i + "," + j + ")");
            }
        }
    }
}
