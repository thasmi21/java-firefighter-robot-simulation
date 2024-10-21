public class Roues extends Robot{

    public Roues(int vitesse_max, int vitesse_de_base, int capacite_reservoir, int temps_recharge, int decharge_reservoir, int temps_decharge, int etat_reservoir, Case position){
        super(Integer.MAX_VALUE, 80, 5000, 600, 100, 5, etat_reservoir, position, new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.HABITAT});
    }

    public double getVitesse(NatureTerrain nature){
        return this.getVitesseBase();
    }

    public void deverserEau()throws RobotIllegalVolException{
        this.setReservoir(100);
    }

    
}