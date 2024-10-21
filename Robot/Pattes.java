public class Pattes extends Robot{

    public Pattes(int vitesse_max, int vitesse_de_base, int capacite_reservoir, int temps_recharge, int decharge_reservoir, int temps_decharge, int etat_reservoir, Case position){
        super(30, 30, Integer.MAX_VALUE, 0, 10, 1, etat_reservoir, position, new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.ROCHE, NatureTerrain.FORET, NatureTerrain.HABITAT});
    }

    public double getVitesse(NatureTerrain actuelle){
        switch (actuelle) {
            case NatureTerrain.ROCHE: 
                return getVitesseBase()/3;
            default:
                return getVitesseBase();
        }
    }

    public void deverserEau()throws RobotIllegalVolException{
        this.setReservoir(10);
    }
}