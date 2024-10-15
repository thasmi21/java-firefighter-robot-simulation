public class Roues extends Robot{

    public Roues(int vitesse_max, int vitesse_de_base, int capacite_reservoir, int temps_recharge, int decharge_reservoir, int temps_decharge, int etat_reservoir, Case position){
        super(Integer.MAX_VALUE, 80, 5000, 600, 100, 5, etat_reservoir, position, new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.HABITAT});
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

    
}