package robots;
import java.util.ArrayList;

import exceptions.*;
import map.*;
import simulation.DeplacerRobotEvenement;
import simulation.Simulateur;

public class Drone extends Robot {

    public Drone(double vitesse_de_base,Case Position){
        super("Drone",Etat.LIBRE,41.66,vitesse_de_base,10000,108000,10000,30,10000,Position,new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.ROCHE,NatureTerrain.EAU,NatureTerrain.FORET,NatureTerrain.HABITAT});
    }

    public double getVitesse(NatureTerrain nature){
        return this.getVitesseBase();
    }

    /** Recharge le résevoir du robot en respectant ses caractéristiques
     * @param carte :carte sur lequel le robot s'exécute */
    public void rechargerReservoir(ArrayList<Case>  watertiles,Carte carte, Simulateur sim) throws RobotIllegalPosException {
        int shortestDistance = Integer.MAX_VALUE;
        Case nearestWaterTile = null;

        for (Case waterTile : watertiles) {
            int distance = this.dijkstra(carte, waterTile).getlongueurChemin(); // Méthode pour obtenir la distance via Dijkstra
    
            if (distance < shortestDistance) {
                shortestDistance = distance;
                nearestWaterTile = waterTile;
            }
        }
        System.out.println(nearestWaterTile);
        Chemin chem = this.dijkstra(carte, nearestWaterTile);
        while(chem.getChemin().size() !=0){
            Case actuelle = chem.getChemin().remove(0);
            long date = chem.getDates().remove(0);
            DeplacerRobotEvenement e = new DeplacerRobotEvenement(this, actuelle, sim.getDateSimulation() + date);
            sim.addEvenement(e);
        } 
        
        //Remplie le résevoir à la capacité maximale de ce type de robot = 10 000L
        try {
            this.setReservoir(this.getCapaciteReservoir()); } 
        catch(RobotIllegalVolException e) {
            System.out.println(e);
        }
        
    }
}    
