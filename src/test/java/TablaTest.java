import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.connect4.game.Tabla;

class TablaTest {

    private Tabla tabla;

    @BeforeEach
    public void setUp() {
        // 7 soros, 6 oszlopos tábla inicializálása
        tabla = new Tabla(7, 6);
    }

    @Test
    public void testTablaInicializalasa() {
        // Ellenőrzi, hogy minden hely üres (' ')
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(' ', tabla.getTabla()[i][j], "A tábla nem üresen inicializálódott.");
            }
        }
    }

    @Test
    public void testLepesElhelyezese() {
        // Az első lépés elhelyezése az 1. oszlopban
        assertTrue(tabla.lehelyez(1, 'S'), "Sikeres lépésnek kellene lennie.");
        // Ellenőrzi, hogy a lépés az alsó sorba került
        assertEquals('S', tabla.getTabla()[6][1], "A lépés nem az alsó sorban lett elhelyezve.");

        /* Még egy lépés elhelyezése ugyanabba az oszlopba, 
        hogy tesztelje hogy helyesen kezeli-e az oszlopokat
        */
        assertTrue(tabla.lehelyez(1, 'P'), "Második lépés elhelyezése sikertelen.");
        assertEquals('P', tabla.getTabla()[5][1], "A második lépés nem a megfelelő sorba került.");
    }

    @Test
    public void testTeleOszlop() {
        // Egy oszlop megtöltése lépésekkel
        for (int i = 0; i < 7; i++) {
            assertTrue(tabla.lehelyez(0, 'S'), "Az oszlop nem töltődött fel helyesen.");
        }
        /*
        Egy újabb lépés elhelyezése ugyanebben 
        az oszlopban, próbaként hogy jól kezeli-e
        */
        assertFalse(tabla.lehelyez(0, 'S'), "A tele oszlopba sikerült lépést elhelyezni.");
    }

    @Test
    public void testEllenorizGyozVizszintes() {
        // Négy egymást követő lépést helyez el vízszintesen
        for (int j = 0; j < 4; j++) {
            tabla.lehelyez(j, 'S');
        }
        // Ellenőrzi a vízszintes győzelmet
        assertTrue(tabla.ellenorizGyoz('S'), "Vízszintes győzelem kimaradt.");
    }

    @Test
    public void testEllenorizGyozFuggoleges() {
        // Négy egymás alatti lépést helyez el egy oszlopban
        int oszlop = 2;
        for (int i = 0; i < 4; i++) {
            tabla.lehelyez(oszlop, 'S');
        }
        // Ellenőrzi a függőleges győzelmet
        assertTrue(tabla.ellenorizGyoz('S'), "Függőleges győzelem kimaradt.");
    }

    @Test
    public void testEllenorizGyozAtlo() {
        // Négy egymást követő lépést helyez el átlósan
        tabla.lehelyez(0, 'S');
        tabla.lehelyez(1, 'P');
        tabla.lehelyez(1, 'S');
        tabla.lehelyez(2, 'P');
        tabla.lehelyez(2, 'P');
        tabla.lehelyez(2, 'S');
        tabla.lehelyez(3, 'P');
        tabla.lehelyez(3, 'P');
        tabla.lehelyez(3, 'P');
        tabla.lehelyez(3, 'S');
        
        // Ellenőrzi az átlós győzelmet
        assertTrue(tabla.ellenorizGyoz('S'), "Átlós győzelem kimaradt.");
    }

    @Test
    void testLehelyezAndEllenorizGyoz() {
        Tabla tabla = new Tabla(6, 7);

        // Lépések elhelyezése
        tabla.lehelyez(0, 'S');
        tabla.lehelyez(1, 'S');
        tabla.lehelyez(2, 'S');
        tabla.lehelyez(3, 'S');

        // Győzelem ellenőrzése
        assertTrue(tabla.ellenorizGyoz('S'));

        // Ellenőrzi, hogy a tábla megfelelően frissült
        char[][] aktualisTabla = tabla.getTabla();
        assertEquals('S', aktualisTabla[5][0]);
        assertEquals('S', aktualisTabla[5][1]);
        assertEquals('S', aktualisTabla[5][2]);
        assertEquals('S', aktualisTabla[5][3]);
    }

    // Ellenőrzi, hogy a tábla helyes méretű-e
    @Test
    void testOszlopHelytelen() {
        Tabla tabla = new Tabla(6, 7);

        // Érvénytelen oszlop elhelyezésének ellenőrzése
        assertFalse(tabla.lehelyez(-1, 'P'));
        assertFalse(tabla.lehelyez(7, 'P'));
    }


    @Test
    public void testErvenytelenLepes() {
        // Megpróbál egy érvénytelen oszlopba lépni
        assertFalse(tabla.lehelyez(-1, 'S'), "Érvénytelen oszlopba sikerült lépést tenni.");
        assertFalse(tabla.lehelyez(7, 'S'), "Érvénytelen oszlopba sikerült lépést tenni.");
    }
}
