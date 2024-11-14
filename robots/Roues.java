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

    /** Recharge le résevoir du robot en respectant ses caractéristiques
     * @param carte :carte sur lequel le robot s'exécute */
    public void rechargerReservoir(Carte carte) throws RobotIllegalPosException {
        int col=this.getPosition().getColonne();
        int lig=this.getPosition().getLigne();
        
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

        //Remplie le résevoir à la capacité maximale de ce type de robot = 5000L
        try {
            this.setReservoir(this.getCapaciteReservoir());
        } catch(RobotIllegalVolException e) {
            System.out.println(e);
        }
        
        //Crée un événement placé à (t actuel) + (temps de remplissage)=10 minutes pouir exécuter le remplissage
        long t = this.simulateur.getDateSimulation();
        Evenement RechargerEauRobotEvenement = new Evenement(this.robot, this.carte, t + 600);
    }
}