package map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseTest {

    @Test
    void testCreationCase() {
        Case c = new Case(2, 3, NatureTerrain.FORET);

        assertEquals(2, c.getLigne());
        assertEquals(3, c.getColonne());
        assertEquals(NatureTerrain.FORET, c.getNature());
    }

    @Test
    void testEquals() {
        Case c1 = new Case(2, 3, NatureTerrain.FORET);
        Case c2 = new Case(2, 3, NatureTerrain.EAU);
        Case c3 = new Case(1, 3, NatureTerrain.FORET);

        assertEquals(c1, c2);
        assertNotEquals(c1, c3);
    }

    @Test
    void testEqualsEau() {
        Case eau = new Case(0, 0, NatureTerrain.EAU);
        Case foret = new Case(0, 1, NatureTerrain.FORET);

        assertTrue(eau.equalsEAU());
        assertFalse(foret.equalsEAU());
    }

    @Test
    void testToString() {
        Case c = new Case(2, 3, NatureTerrain.FORET);

        assertEquals("[col : 3 lig : 2]", c.toString());
    }

}