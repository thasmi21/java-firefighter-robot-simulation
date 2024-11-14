package robots;
import exceptions.*;
import map.Carte;
import map.Case;
import map.NatureTerrain;

public class Drone extends Robot {

    public Drone(double vitesse_de_base,Case Position){
        super("Drone",Etat.LIBRE,41.66,vitesse_de_base,10000,108000,10000,30,10000,Position,new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.ROCHE,NatureTerrain.EAU,NatureTerrain.FORET,NatureTerrain.HABITAT});
    }

    public double getVitesse(NatureTerrain nature){
        return this.getVitesseBase();
    }

    /** Recharge le résevoir du robot en respectant ses caractéristiques
     * @param carte :carte sur lequel le robot s'exécute */
    public void rechargerReservoir(Carte carte) throws RobotIllegalPosException {

        //Vérifie qu'il y a bien une case EAU en dessous de lui, sinon lève une exception    
        NatureTerrain caseActuelle = this.getPosition().getNature();
        if (caseActuelle != NatureTerrain.EAU){ 
            throw new IndexOutOfBoundsException("Le robot n'est pas autorisé à remplir son réservoir sur cette case.\n");
        }
        
        //Remplie le résevoir à la capacité maximale de ce type de robot = 10 000L
        try {
            this.setReservoir(this.getCapaciteReservoir()); } 
        catch(RobotIllegalVolException e) {
            System.out.println(e);
        }
        
    }
}    
