package map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IncendieTest {

    @Test
    void testCreationIncendie() {
        Case position = new Case(1, 2, NatureTerrain.FORET);
        Incendie incendie = new Incendie(position, 500);

        assertEquals(position, incendie.getPosition());
        assertEquals(500, incendie.getVolExtinction());
    }

    @Test
    void testDiminutionFeu() {
        Case position = new Case(1, 2, NatureTerrain.FORET);
        Incendie incendie = new Incendie(position, 500);

        int restant = incendie.setFeuDec(200);

        assertEquals(300, restant);
        assertEquals(300, incendie.getVolExtinction());
    }

    @Test
    void testComparaisonIncendies() {
        Incendie grand = new Incendie(
            new Case(0, 0, NatureTerrain.FORET), 1000);

        Incendie petit = new Incendie(
            new Case(0, 1, NatureTerrain.FORET), 200);

        assertTrue(grand.compareTo(petit) < 0);
        assertTrue(petit.compareTo(grand) > 0);
    }
}