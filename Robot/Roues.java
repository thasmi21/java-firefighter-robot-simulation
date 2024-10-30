public class Roues extends Robot{

    public Roues(int vitesse_de_base, Case position){
        super("Roue",Integer.MAX_VALUE, vitesse_de_base, 5000, 600, 100, 5, 5000, position, new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.HABITAT});
    }

    public double getVitesse(NatureTerrain nature){
        return this.getVitesseBase();
    }

    public void deverserEau()throws RobotIllegalVolException{
        this.setReservoir(100);
    }

    
}