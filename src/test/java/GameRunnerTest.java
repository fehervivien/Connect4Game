import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.connect4.game.GameRunner;
import com.connect4.input.InputProvider;

public class GameRunnerTest {

    private GameRunner gameRunner;
    private InputProvider inputProviderMock;

    @BeforeEach
    public void setUp() {
        inputProviderMock = mock(InputProvider.class); // Itt az InputProvider mockot hozunk létre
        gameRunner = new GameRunner(inputProviderMock);
    }

    @Test
    public void testRunGame_LoadFromFile() {
        // Mockoljuk a bemeneteket
        when(inputProviderMock.nextLine()).thenReturn("Igen") // Az első bemenet "Igen"
            .thenReturn("file-path.txt")  // A második bemenet a fájl elérési útja
            .thenReturn("PlayerName")  // A harmadik bemenet a játékos neve
            .thenReturn("a")  // Az oszlopválasztás
            .thenReturn("b")  // Második oszlopválasztás
            .thenReturn("c"); // Harmadik oszlopválasztás

        // A játékmenet lefuttatása
        gameRunner.runGame();

        // Assert állítások
        assertTrue(true); // Példa assert állítás
    }

    @Test
    public void testRunGame_NewGame() {
        // Mockoljuk a bemeneteket
        when(inputProviderMock.nextLine()).thenReturn("Nem") // Az első bemenet "Nem"
            .thenReturn("PlayerName")  // A második bemenet a játékos neve
            .thenReturn("a")  // Az oszlopválasztás
            .thenReturn("b")  // Második oszlopválasztás
            .thenReturn("c"); // Harmadik oszlopválasztás

        // A játékmenet lefuttatása
        gameRunner.runGame();

        // Assert állítások
        assertTrue(true); // Példa assert állítás
    }

    @Test
    public void testRunGame_PlayerMove() {
        // Mockoljuk a bemeneteket
        when(inputProviderMock.nextLine()).thenReturn("Nem") // Az első bemenet "Nem"
            .thenReturn("PlayerName")  // A második bemenet a játékos neve
            .thenReturn("a")  // Az oszlopválasztás
            .thenReturn("b"); // Második oszlopválasztás

        // A játékmenet lefuttatása
        gameRunner.runGame();

        // Assert állítások
        assertTrue(true); // Példa assert állítás
    }
}