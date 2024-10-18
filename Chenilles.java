public class Chenilles extends Robot{

    public Chenilles(int vitesse_max, int vitesse_de_base,int capacite_reservoir,int temps_recharge,int decharge_reservoir,int temps_decharge,int etat_reservoir,Case position){
        super(80, 60, 2000, 300, 100, 8, etat_reservoir, position, new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.FORET, NatureTerrain.HABITAT});
    }

    @Override
    public int getVitesse(NatureTerrain actuelle){
        switch (actuelle) {
            case NatureTerrain.FORET: 
                return getVitesseBase()/2;
            default:
                return getVitesseBase();
        }
    }
}
