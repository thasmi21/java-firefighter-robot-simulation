package robots;
import exceptions.RobotIllegalPosException;
import map.Case;
import map.NatureTerrain;

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

    public void rechargerReservoir() throws RobotIllegalPosException {
        int col=this.getPosition().getColonne();
        int lig=this.getPosition().getLigne();
        
        int verifEau=0;
        if (carte.getVoisin(this.getPosition(), NORD).getNature()==EAU){verifEau=1;}
        else if (carte.getVoisin(this.getPosition(), SUD).getNature()==EAU){verifEau=1;}
        else if (carte.getVoisin(this.getPosition(), EST).getNature()==EAU){verifEau=1;}
        else if (carte.getVoisin(this.getPosition(), OUEST).getNature()==EAU){verifEau=1;}
        
        if (verifEau!=1){
            throw new IndexOutOfBoundsException("Le robot n'est pas autorisé à remplir son réservoir sur cette case.\n");
        }

        this.setReservoir(this.capacite_reservoir);
        //Récupérer la date actuelle
        //Créer un évènement à t+300 :RechargerEauRobotEvenement
    }
}
