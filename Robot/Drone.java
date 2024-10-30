public class Drone extends Robot {

    public Drone(int vitesse_de_base,Case Position){
        super("Drone",150,vitesse_de_base,10000,108000,10000,30,10000,Position,new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.ROCHE,NatureTerrain.EAU,NatureTerrain.FORET,NatureTerrain.HABITAT});
    }

    public double getVitesse(NatureTerrain nature){
        return this.getVitesseBase();
    }

    public void deverserEau()throws RobotIllegalVolException{
        this.setReservoir(this.getCapaciteReservoir());
    }
}    
