package robots;
import exceptions.RobotIllegalPosException;
import exceptions.RobotIllegalVolException;
import map.Case;
import map.Carte;
import map.NatureTerrain;
import simulation.Simulateur;
import map.Direction;

import java.util.ArrayList;

import exceptions.CarteHorsCarteException;

public class Chenilles extends Robot{

    public Chenilles(double vitesse_de_base,Case position){
        super("Chenille",Etat.LIBRE,22.2, vitesse_de_base, 2000, 300, 100, 8, 2000, position, new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.FORET, NatureTerrain.HABITAT});
    }


    public double getVitesse(NatureTerrain actuelle){
        switch (actuelle) {
            case EAU:
                return 0;
            case ROCHE:
                return 0;
            case FORET: 
                return getVitesseBase()/2;
            default:
                return getVitesseBase();
        }
    }

    /** Recharge le résevoir du robot en respectant ses caractéristiques
     * @param carte :carte sur lequel le robot s'exécute */
    public void rechargerReservoir(ArrayList<Case>  watertiles,Carte carte, Simulateur sim) throws RobotIllegalPosException {
        int verifEau=0;

        //Vérifie qu'il y a bien une case eau autours de lui, sinon lève une exception
        try{
            if (carte.getVoisin(this.getPosition(), Direction.NORD).getNature()==NatureTerrain.EAU){verifEau=1;}
            else if (carte.getVoisin(this.getPosition(), Direction.SUD).getNature()==NatureTerrain.EAU){verifEau=1;}
            else if (carte.getVoisin(this.getPosition(), Direction.EST).getNature()==NatureTerrain.EAU){verifEau=1;}
            else if (carte.getVoisin(this.getPosition(), Direction.OUEST).getNature()==NatureTerrain.EAU){verifEau=1;}
        } catch(CarteHorsCarteException e) {
            System.out.println(e);
        }
        
        if (verifEau!=1){ //Lève une exception si aucune des 4 cases autours du robot n'est de l'eau
            throw new IndexOutOfBoundsException("Le robot n'est pas autorisé à remplir son réservoir sur cette case.\n");
        }

        //Remplie le résevoir à la capacité maximale de ce type de robot = 2000L
        try {
            this.setReservoir(this.getCapaciteReservoir());
        } catch(RobotIllegalVolException e) {
            System.out.println(e);
        }
        
    }
}
