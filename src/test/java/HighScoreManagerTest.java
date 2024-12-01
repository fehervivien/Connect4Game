import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.connect4.highscore.HighScore;
import com.connect4.highscore.HighScoreManager;

public class HighScoreManagerTest {

    private HighScoreManager highScoreManager;

    @BeforeEach
    public void setUp() {
        // A tesztek előtt új HighScoreManager példány létrehozása
        highScoreManager = new HighScoreManager();
    }

    @Test
    public void testBetoltHighScores() {
        // Teszteli, hogy a pontszámok betöltődnek a fájlból
        // A mock fájl létrehozása
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("highscores.txt"))) {
            bw.write("Jatekos1: 100\n");
            bw.write("Jatekos2: 150\n");
        } catch (IOException e) {
            fail("Hiba a mock fájl írása során: " + e.getMessage());
        }

        // Új HighScoreManager példány létrehozása, amely betölti a fájl tartalmát
        highScoreManager = new HighScoreManager();

        // Ellenőrizzük, hogy a betöltött pontszámok helyesek
        assertEquals(2, highScoreManager.getHighScores().size());
        assertEquals("Jatekos1", highScoreManager.getHighScores().get(0).getNev());
        assertEquals(100, highScoreManager.getHighScores().get(0).getPontszam());
        assertEquals("Jatekos2", highScoreManager.getHighScores().get(1).getNev());
        assertEquals(150, highScoreManager.getHighScores().get(1).getPontszam());
    }

    @Test
    public void testMentesHighScore() {
        // Játékos pontszámának mentése
        highScoreManager.mentesHighScore("Jatekos1", 100);
        assertEquals(1, highScoreManager.getHighScores().size());
        assertEquals("Jatekos1", highScoreManager.getHighScores().get(0).getNev());
        assertEquals(100, highScoreManager.getHighScores().get(0).getPontszam());

        // Másik pontszám hozzáadása
        highScoreManager.mentesHighScore("Jatekos2", 150);
        assertEquals(2, highScoreManager.getHighScores().size());

        // Printeljük ki a lista tartalmát a hibaelhárítás miatt
        highScoreManager.getHighScores().forEach(hs -> {
            System.out.println("Név: " + hs.getNev() + ", Pontszám: " + hs.getPontszam());
        });

        // Ellenőrizzük, hogy a pontszámok rendezve vannak-e
        assertEquals("Jatekos2", highScoreManager.getHighScores().get(0).getNev());
        assertEquals(150, highScoreManager.getHighScores().get(0).getPontszam());

        // Az első játékos pontszámának növelése
        highScoreManager.mentesHighScore("Jatekos1", 50);
        assertEquals(150, highScoreManager.getHighScores().get(0).getPontszam()); // Jatekos2 pontszáma
        assertEquals("Jatekos1", highScoreManager.getHighScores().get(1).getNev()); // Második elem
        assertEquals(150, highScoreManager.getHighScores().get(1).getPontszam()); // Jatekos1 pontszáma
    }

    @Test
    public void testKiirHighScores() {
        // Játékos pontszámának mentése
        highScoreManager.mentesHighScore("Jatekos1", 100);
        highScoreManager.mentesHighScore("Jatekos2", 150);

        // Kimenet ellenőrzése
        highScoreManager.kiirHighScores();
        /*
         * Itt nem lehet közvetlen kimenetet ellenőrizni, de biztosítom, hogy ne
         * dobjon kivételt
         */
        assertDoesNotThrow(() -> highScoreManager.kiirHighScores());
    }

    @Test
    public void testFindHighScoreByName() {
        highScoreManager.mentesHighScore("Jatekos1", 100);
        highScoreManager.mentesHighScore("Jatekos2", 150);

        // Ellenőrzi, hogy a keresett névvel rendelkező pontszámot megtalálja
        HighScore foundScore1 = highScoreManager.findHighScoreByName("Jatekos1");
        assertNotNull(foundScore1);
        assertEquals("Jatekos1", foundScore1.getNev());
        assertEquals(100, foundScore1.getPontszam());

        // Ellenőrzi, hogy a másik játékos pontszámát is megtalálja
        HighScore foundScore2 = highScoreManager.findHighScoreByName("Jatekos2");
        assertNotNull(foundScore2);
        assertEquals("Jatekos2", foundScore2.getNev());
        assertEquals(150, foundScore2.getPontszam());

        // Ellenőrzi, hogy a keresés kis- és nagybetű független
        HighScore foundScore3 = highScoreManager.findHighScoreByName("jatekos1");
        assertNotNull(foundScore3);
        assertEquals("Jatekos1", foundScore3.getNev());

        // Ellenőrzi, hogy nem található név esetén null-t kapunk
        HighScore notFoundScore = highScoreManager.findHighScoreByName("Ismeretlen");
        assertNull(notFoundScore);
    }

    @AfterEach
    public void tearDown() {
        // A tesztek után a mock fájl törlése
        File file = new File("highscores.txt");
        if (file.exists()) {
            file.delete();
        }
    }
}
