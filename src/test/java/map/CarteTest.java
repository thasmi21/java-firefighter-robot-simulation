package map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import exceptions.CarteHorsCarteException;

class CarteTest {

    @Test
    void testCreationCarte() {
        Carte carte = new Carte(3, 4, 10);

        assertEquals(3, carte.getNbLignes());
        assertEquals(4, carte.getNbColonnes());
        assertEquals(10, carte.getTailleCases());
    }

    @Test
    void testSetEtGetCase() throws Exception {
        Carte carte = new Carte(3, 3);

        carte.setCase(1, 2, NatureTerrain.FORET);

        Case c = carte.getCase(1, 2);

        assertEquals(1, c.getLigne());
        assertEquals(2, c.getColonne());
        assertEquals(NatureTerrain.FORET, c.getNature());
    }

    @Test
    void testVoisinExiste() throws Exception {
        Carte carte = new Carte(3, 3);

        carte.setCase(1, 1, NatureTerrain.TERRAIN_LIBRE);
        Case centre = carte.getCase(1, 1);

        assertTrue(carte.voisinExiste(centre, Direction.NORD));
        assertTrue(carte.voisinExiste(centre, Direction.SUD));
        assertTrue(carte.voisinExiste(centre, Direction.EST));
        assertTrue(carte.voisinExiste(centre, Direction.OUEST));
    }

    @Test
    void testVoisinHorsCarte() throws Exception {
        Carte carte = new Carte(3, 3);

        carte.setCase(0, 0, NatureTerrain.TERRAIN_LIBRE);
        Case coin = carte.getCase(0, 0);

        assertFalse(carte.voisinExiste(coin, Direction.NORD));
        assertFalse(carte.voisinExiste(coin, Direction.OUEST));
    }

    @Test
    void testSetCaseHorsLimites() {
        Carte carte = new Carte(3, 3);

        assertThrows(
            CarteHorsCarteException.class,
            () -> carte.setCase(3, 1, NatureTerrain.FORET)
        );

        assertThrows(
            CarteHorsCarteException.class,
            () -> carte.setCase(1, 3, NatureTerrain.FORET)
        );
    }

    @Test
    void testGetCaseHorsLimites() {
        Carte carte = new Carte(3, 3);

        assertThrows(
            CarteHorsCarteException.class,
            () -> carte.getCase(3, 1)
        );

        assertThrows(
            CarteHorsCarteException.class,
            () -> carte.getCase(1, 3)
        );
    }

    @Test
    void testVoisinsSurLesBords() throws Exception {
        Carte carte = new Carte(3, 3);

        carte.setCase(0, 0, NatureTerrain.TERRAIN_LIBRE);
        carte.setCase(2, 2, NatureTerrain.TERRAIN_LIBRE);

        Case hautGauche = carte.getCase(0, 0);
        Case basDroite = carte.getCase(2, 2);

        // Coin supérieur gauche
        assertFalse(carte.voisinExiste(hautGauche, Direction.NORD));
        assertFalse(carte.voisinExiste(hautGauche, Direction.OUEST));
        assertTrue(carte.voisinExiste(hautGauche, Direction.SUD));
        assertTrue(carte.voisinExiste(hautGauche, Direction.EST));

        // Coin inférieur droit
        assertTrue(carte.voisinExiste(basDroite, Direction.NORD));
        assertTrue(carte.voisinExiste(basDroite, Direction.OUEST));
        assertFalse(carte.voisinExiste(basDroite, Direction.SUD));
        assertFalse(carte.voisinExiste(basDroite, Direction.EST));
    }
}