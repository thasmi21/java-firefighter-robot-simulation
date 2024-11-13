package robots;
import exceptions.RobotIllegalPosException;
import exceptions.RobotIllegalVolException;
import map.Case;
import map.Carte;
import map.NatureTerrain;
import map.Direction;
import exceptions.CarteHorsCarteException;
import exceptions.RobotIllegalVolException;

public class Chenilles extends Robot{

    public Chenilles(double vitesse_de_base,Case position){
        super("Chenille",Etat.LIBRE,22.2, vitesse_de_base, 2000, 300, 100, 8, 2000, position, new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.FORET, NatureTerrain.HABITAT});
    }


    public double getVitesse(NatureTerrain actuelle){
        switch (actuelle) {
            case NatureTerrain.EAU:
                return 0;
            case NatureTerrain.ROCHE:
                return 0;
            case NatureTerrain.FORET: 
                return getVitesseBase()/2;
            default:
                return getVitesseBase();
        }
    }

    public void rechargerReservoir(Carte carte) throws RobotIllegalPosException {
        int col=this.getPosition().getColonne();
        int lig=this.getPosition().getLigne();
        
        int verifEau=0;
        try{
            if (carte.getVoisin(this.getPosition(), Direction.NORD).getNature()==NatureTerrain.EAU){verifEau=1;}
            else if (carte.getVoisin(this.getPosition(), Direction.SUD).getNature()==NatureTerrain.EAU){verifEau=1;}
            else if (carte.getVoisin(this.getPosition(), Direction.EST).getNature()==NatureTerrain.EAU){verifEau=1;}
            else if (carte.getVoisin(this.getPosition(), Direction.OUEST).getNature()==NatureTerrain.EAU){verifEau=1;}
        } catch(CarteHorsCarteException e) {
            System.out.println(e);
        }
        
        if (verifEau!=1){
            throw new IndexOutOfBoundsException("Le robot n'est pas autorisé à remplir son réservoir sur cette case.\n");
        }

        try {
            this.setReservoir(this.getCapaciteReservoir());
        } catch(RobotIllegalVolException e) {
            System.out.println(e);
        }
        //Récupérer la date actuelle
        //Créer un évènement à t+300 :RechargerEauRobotEvenement
    }
}
