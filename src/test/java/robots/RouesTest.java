package robots;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import map.Case;
import map.NatureTerrain;
import exceptions.RobotIllegalPosException;
import exceptions.RobotIllegalVolException;

class RouesTest {

    @Test
    void testCreationRobotRoues() {
        Case depart = new Case(1, 1, NatureTerrain.TERRAIN_LIBRE);
        Roues robot = new Roues(30.0, depart);

        assertEquals("Roue", robot.getType());
        assertEquals(Etat.LIBRE, robot.getEtat());
        assertEquals(41.6, robot.getVitesseMax(), 0.001);
        assertEquals(30.0, robot.getVitesseBase(), 0.001);

        assertEquals(5000, robot.getCapaciteReservoir());
        assertEquals(5000, robot.getEtatReservoir());
        assertEquals(600, robot.getTempsRecharge());
        assertEquals(100, robot.getDechargeReservoir());
        assertEquals(5, robot.getTempsDecharge());

        assertEquals(depart, robot.getPosition());
    }

    @Test
    void testVitesseSelonTerrain() {
        Roues robot = new Roues(
            30.0,
            new Case(0, 0, NatureTerrain.TERRAIN_LIBRE)
        );

        assertEquals(30.0,
            robot.getVitesse(NatureTerrain.TERRAIN_LIBRE), 0.001);

        assertEquals(30.0,
            robot.getVitesse(NatureTerrain.HABITAT), 0.001);

        assertEquals(0.0,
            robot.getVitesse(NatureTerrain.EAU), 0.001);

        assertEquals(0.0,
            robot.getVitesse(NatureTerrain.FORET), 0.001);
    }

    @Test
    void testDeplacementTerrainAutorise() throws RobotIllegalPosException {
        Roues robot = new Roues(
            30.0,
            new Case(0, 0, NatureTerrain.TERRAIN_LIBRE)
        );

        Case destination =
            new Case(0, 1, NatureTerrain.HABITAT);

        robot.setPosition(destination);

        assertEquals(destination, robot.getPosition());
    }

    @Test
    void testDeplacementTerrainInterdit() {
        Roues robot = new Roues(
            30.0,
            new Case(0, 0, NatureTerrain.TERRAIN_LIBRE)
        );

        Case eau = new Case(0, 1, NatureTerrain.EAU);

        assertThrows(
            RobotIllegalPosException.class,
            () -> robot.setPosition(eau)
        );
    }

    @Test
    void testModificationReservoir() throws RobotIllegalVolException {
        Roues robot = new Roues(
            30.0,
            new Case(0, 0, NatureTerrain.TERRAIN_LIBRE)
        );

        robot.setReservoir(2500);

        assertEquals(2500, robot.getEtatReservoir());
    }

    @Test
    void testVolumeReservoirInvalide() {
        Roues robot = new Roues(
            30.0,
            new Case(0, 0, NatureTerrain.TERRAIN_LIBRE)
        );

        assertThrows(
            RobotIllegalVolException.class,
            () -> robot.setReservoir(6000)
        );

        assertThrows(
            RobotIllegalVolException.class,
            () -> robot.setReservoir(-1)
        );
    }

    @Test
    void testDeverserEau() {
        Roues robot = new Roues(
            30.0,
            new Case(0, 0, NatureTerrain.TERRAIN_LIBRE)
        );

        int volume = robot.deverserEau();

        assertEquals(4900, volume);
        assertEquals(4900, robot.getEtatReservoir());
    }

    @Test
    void testDeverserEauReservoirPresqueVide()
            throws RobotIllegalVolException {

        Roues robot = new Roues(
            30.0,
            new Case(0, 0, NatureTerrain.TERRAIN_LIBRE)
        );

        robot.setReservoir(50);

        int volume = robot.deverserEau();

        assertEquals(50, volume);
        assertEquals(50, robot.getEtatReservoir());
    }

    @Test
    void testDeverserEauFrontiere()
            throws RobotIllegalVolException {

        Roues robot = new Roues(
            30.0,
            new Case(0, 0, NatureTerrain.TERRAIN_LIBRE)
        );

        robot.setReservoir(100);

        int volume = robot.deverserEau();

        assertEquals(100, volume);
        assertEquals(100, robot.getEtatReservoir());
    }

    @Test
    void testSetReservoirVolumeEgalVolumeActuel()
            throws RobotIllegalVolException {

        Roues robot = new Roues(
            30.0,
            new Case(0, 0, NatureTerrain.TERRAIN_LIBRE)
        );

        robot.setReservoir(5000);

        assertEquals(5000, robot.getEtatReservoir());
    }

}