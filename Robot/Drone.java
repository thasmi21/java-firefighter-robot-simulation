public class Drone extends Robot {

    public Drone(int vitesse_max,int vitesse_de_base,int capacite_reservoir,int temps_recharge,int decharge_reservoir,int tmps_decharge,int etat_reservoir,Case Position,NatureTerrain terrainsAcc[]){
        super(150,100,10000,108000,10000,30,etat_reservoir,Position,new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.ROCHE,NatureTerrain.EAU,NatureTerrain.FORET,NatureTerrain.HABITAT});
    }

    public double getVitesse(NatureTerrain nature){
        return this.getVitesseBase();
    }

    public void deverserEau()throws RobotIllegalVolException{
        this.setReservoir(this.getCapaciteReservoir());
    }
}    
