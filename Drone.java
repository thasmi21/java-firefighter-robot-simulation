public class Drone extends Robot {
    private enum Terrain_accessible{TERRAIN_LIBRE, ROCHE,EAU,FORET,HABITAT}

    public Drone(int vitesse_max,int capacite_reservoir,int temps_recharge,int decharge_reservoir,int tmps_decharge,int etat_reservoir,Case Position){
        super(100,10000,108000,10000,30,etat_reservoir,Position);
    }

    @Override
    public int Deverser_Eau(int vol){
        if(this.change_etat_reservoir(vol) == -1){
            return -1;
        }
        else{
            return 0;
        }
    }
}    
