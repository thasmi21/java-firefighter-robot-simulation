package robots;
import exceptions.RobotIllegalPosException;
import map.Carte;
import map.Case;
import map.NatureTerrain;

public class Roues extends Robot{

    public Roues(double vitesse_de_base, Case position){
        super("Roue",Etat.LIBRE,41.6, vitesse_de_base, 5000, 600, 100, 5, 5000, position, new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.HABITAT});
    }

    public double getVitesse(NatureTerrain actuelle){
        switch (actuelle) {
            case NatureTerrain.HABITAT:
                return getVitesseBase();
            case NatureTerrain.TERRAIN_LIBRE: 
                return getVitesseBase();
            default:
                return 0;
        }
    }

    public void rechargerReservoir(Carte carte) throws RobotIllegalPosException {}
}