import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.connect4.game.Tabla;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class TablaTest {

    @Test
    public void testLehelyez() {
        Tabla tabla = new Tabla(6, 7);
        assertTrue(tabla.lehelyez(0, 'X'));
        Assertions.assertFalse(tabla.lehelyez(-1, 'X')); // Érvénytelen oszlop
        Assertions.assertFalse(tabla.lehelyez(7, 'X')); // Érvénytelen oszlop
    }

    @Test
    public void testGyoztes() {
        Tabla tabla = new Tabla(6, 7);
        tabla.lehelyez(0, 'X');
        tabla.lehelyez(0, 'X');
        tabla.lehelyez(0, 'X');
        tabla.lehelyez(0, 'X');
        assertTrue(tabla.ellenorizGyoz('X'));
    }
}
