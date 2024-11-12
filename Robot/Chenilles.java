public class Chenilles extends Robot{

    public Chenilles(double vitesse_de_base,Case position){
        super("Chenille",22.2, vitesse_de_base, 2000, 300, 100, 8, 2000, position, new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.FORET, NatureTerrain.HABITAT});
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
}
