import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.connect4.game.GameRunner;

import java.io.StringReader;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameRunnerTest {

    private GameRunner gameRunner;

    @BeforeEach
    public void setUp() {
        // A setUp metódus nem fog semmilyen mockolást végezni
    }

    @Test
    public void testRunGame_LoadFromFile() {
        // Szimuláljuk a felhasználói inputokat egy StringReader-rel
        String input = "Igen\nfile-path.txt\n2\n"; // Fájl betöltése, majd oszlopválasztás
        Scanner scanner = new Scanner(new StringReader(input));
        gameRunner = new GameRunner(scanner);

        // A runGame metódus meghívása
        gameRunner.runGame();

        // Assert-ek vagy más logikai ellenőrzések itt szükségesek
        assertTrue(true);  // Csak példa, a tényleges ellenőrzést hozzá kell adni
    }

    @Test
    public void testRunGame_NewGame() {
        // Szimuláljuk, hogy a felhasználó nem szeretne fájlból betölteni
        String input = "Nem\n2\n"; // Új játék, majd oszlopválasztás
        Scanner scanner = new Scanner(new StringReader(input));
        gameRunner = new GameRunner(scanner);

        // A runGame metódus meghívása
        gameRunner.runGame();

        // Assert-ek vagy más logikai ellenőrzések itt szükségesek
        assertTrue(true);  // Csak példa, a tényleges ellenőrzést hozzá kell adni
    }

    @Test
    public void testRunGame_PlayerMove() {
        // Szimuláljuk, hogy a felhasználó új játékot szeretne és kiválaszt egy oszlopot
        String input = "Nem\n2\n"; // Új játék, majd oszlopválasztás
        Scanner scanner = new Scanner(new StringReader(input));
        gameRunner = new GameRunner(scanner);

        // A runGame metódus meghívása
        gameRunner.runGame();

        // Assert-ek vagy más logikai ellenőrzések itt szükségesek
        assertTrue(true);  // Csak példa, a tényleges ellenőrzést hozzá kell adni
    }
}
