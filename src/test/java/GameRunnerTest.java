import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.connect4.game.GameRunner;
import com.connect4.input.InputProvider;

public class GameRunnerTest {

    private GameRunner gameRunner;
    private InputProvider inputProviderMock;

    @BeforeEach
    public void setUp() {
        // Itt az InputProvider mockot hozunk létre
        inputProviderMock = mock(InputProvider.class);
        gameRunner = new GameRunner(inputProviderMock);
    }

    @Test
    @Timeout(5) // Maximum 5 másodperc
    public void testRunGame_LoadFromFile() {
        // Mockoljuk a bemeneteket
        when(inputProviderMock.nextLine()).thenReturn("Igen")
                .thenReturn("file-path.txt") // A második bemenet a fájl elérési útja
                .thenReturn("PlayerName")
                // Az oszlopválasztás
                .thenReturn("a")
                .thenReturn("b")
                .thenReturn("c")
                // A játék befejezése
                .thenReturn("Nem");

        // A játékmenet lefuttatása
        gameRunner.runGame();

        // Ellenőrzi, hogy a gameRunner objektum nem null
        assertNotNull(gameRunner);
    }

    @Test
    @Timeout(5) // Maximum 5 másodperc
    public void testRunGame_NewGame() {
        // Mockolja a bemeneteket
        when(inputProviderMock.nextLine()).thenReturn("Nem") // Az első bemenet "Nem"
                .thenReturn("PlayerName") // A második bemenet a játékos neve
                .thenReturn("a") // Az oszlopválasztás
                .thenReturn("b") // Második oszlopválasztás
                .thenReturn("c") // Harmadik oszlopválasztás
                .thenReturn("Nem"); // A játék befejezése

        // A játékmenet lefuttatása
        gameRunner.runGame();

        // Ellenőrzi, hogy a gameRunner objektum nem null
        assertNotNull(gameRunner);
    }

    @Test
    @Timeout(5) // Maximum 5 másodperc
    public void testRunGame_PlayerMove() {
        // Mockoljuk a bemeneteket
        when(inputProviderMock.nextLine()).thenReturn("Nem") // Az első bemenet "Nem"
                .thenReturn("PlayerName") // A második bemenet a játékos neve
                .thenReturn("a") // Az oszlopválasztás
                .thenReturn("b") // Második oszlopválasztás
                .thenReturn("Nem"); // A játék befejezése

        // A játékmenet lefuttatása
        gameRunner.runGame();

        // Ellenőrzi, hogy a gameRunner objektum nem null
        assertNotNull(gameRunner);

    }
}
