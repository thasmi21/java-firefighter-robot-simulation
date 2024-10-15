public class Drone extends Robot {

    public Drone(int vitesse_max,int vitesse_de_base,int capacite_reservoir,int temps_recharge,int decharge_reservoir,int tmps_decharge,int etat_reservoir,Case Position,NatureTerrain terrainsAcc[]){
        super(150,100,10000,108000,10000,30,etat_reservoir,Position,new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.ROCHE,NatureTerrain.EAU,NatureTerrain.FORET,NatureTerrain.HABITAT});
    }

    @Override
    public int deverserEau(int vol){
        if(this.changeEtatReservoir(vol) == -1){
            return -1;
        }
        else{
            return 0;
        }
    }
}    
