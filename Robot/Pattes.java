public class Pattes extends Robot{

    public Pattes(Case position){
        super("Patte",41.6, 8.33, Integer.MAX_VALUE, 0, 10, 1, Integer.MAX_VALUE, position, new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.ROCHE, NatureTerrain.FORET, NatureTerrain.HABITAT});
    }

    public double getVitesse(NatureTerrain actuelle){
        switch (actuelle) {
            case NatureTerrain.EAU:
                return 0.0;
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