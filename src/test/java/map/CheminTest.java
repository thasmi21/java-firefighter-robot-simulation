package map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

class CheminTest {

    @Test
    void testCreationEtGetters() {
        Case c1 = new Case(0, 0, NatureTerrain.TERRAIN_LIBRE);
        Case c2 = new Case(0, 1, NatureTerrain.FORET);

        ArrayList<Case> cases =
            new ArrayList<>(Arrays.asList(c1, c2));

        ArrayList<Long> dates =
            new ArrayList<>(Arrays.asList(0L, 10L));

        Chemin chemin = new Chemin(cases, dates, 2, 10L);

        assertEquals(cases, chemin.getChemin());
        assertEquals(dates, chemin.getDates());
        assertEquals(2, chemin.getlongueurChemin());
        assertEquals(10L, chemin.getTempsTrajet());
    }

    @Test
    void testAddCase() {
        ArrayList<Case> cases = new ArrayList<>();
        ArrayList<Long> dates = new ArrayList<>();

        Chemin chemin = new Chemin(cases, dates, 0, 0L);

        Case c = new Case(1, 2, NatureTerrain.EAU);
        chemin.addCase(c);

        assertEquals(1, chemin.getChemin().size());
        assertEquals(c, chemin.getChemin().get(0));
    }

    @Test
    void testToString() {
        Case c1 = new Case(0, 0, NatureTerrain.TERRAIN_LIBRE);
        Case c2 = new Case(0, 1, NatureTerrain.FORET);

        ArrayList<Case> cases =
            new ArrayList<>(Arrays.asList(c1, c2));

        ArrayList<Long> dates =
            new ArrayList<>(Arrays.asList(0L, 15L));

        Chemin chemin = new Chemin(cases, dates, 2, 15L);

        String texte = chemin.toString();

        assertTrue(texte.contains("etape 0"));
        assertTrue(texte.contains("etape 1"));
        assertTrue(texte.contains("temps 15"));
        assertTrue(texte.contains("longueurChemin = 2"));
        assertTrue(texte.contains("temps total 15"));
    }

    @Test
    void testToStringCheminVide() {
        ArrayList<Case> cases = new ArrayList<>();
        ArrayList<Long> dates = new ArrayList<>();

        Chemin chemin = new Chemin(cases, dates, 0, 0L);

        assertEquals(
            "longueurChemin = 0et le temps total 0\n",
            chemin.toString()
        );
    }

}