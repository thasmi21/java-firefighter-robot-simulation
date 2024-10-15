public class Chenilles extends Robot{

    public Chenilles(int vitesse_max, int vitesse_de_base, int capacite_reservoir,int temps_recharge,int decharge_reservoir,int temps_decharge,int etat_reservoir,Case position){
        super(80, 60, 2000, 300, 100, 8, etat_reservoir, position, new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.FORET, NatureTerrain.HABITAT});
    }

    @Override
    public int deverser_Eau(int vol){
        if(this.change_Etat_Reservoir(vol) == -1){
            return -1;
        }
        else{
            wait(this.temps_decharge * 1000);
            setDecrementFeu(vol);
            return 0;
        }
    }

    public int set_Position(Position){
        if 
    }
}
