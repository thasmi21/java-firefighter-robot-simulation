public abstract class Robot{
    private int vitesse_max;            // vitesse de déplacement max du robot (km/h)
    private int capacite_reservoir;      // capacité max du reservoir (enL)
    private int temps_recharge;          // temps pour recharger completement le reservoir (s)
    private int decharge_reservoir;      // quantité déversée pour une extinction (en L)
    private int tmps_decharge;           // temps qu'il faut pour verser la quantité d'eau pour une extinction (en s)
    private int etat_reservoir;          //  etat actuel du reservoir entre 0 et capacité reservoir (en L)
    private Case Position;




    public Robot(int vitesse_max,int capacite_reservoir,int temps_recharge,int decharge_reservoir,int tmps_decharge,int etat_reservoir,Case Position){
        this.vitesse_max = vitesse_max;
        this.capacite_reservoir = capacite_reservoir;
        this.temps_recharge = temps_recharge;
        this.decharge_reservoir = decharge_reservoir;
        this.tmps_decharge = tmps_decharge;
        this.etat_reservoir = etat_reservoir;
        this.Position = Position;
    }

    public int get_Vitesse_max(){
        return this.vitesse_max;
    }
    public int get_Capacite_Reservoir(){
        return this.capacite_reservoir;
    }
    public int get_Temps_Recharge(){
        return this.temps_recharge;
    }
    public int get_Decharge_Reservoir(){
        return this.decharge_reservoir;
    }
    public int get_Tmps_Decharge(){
        return this.tmps_decharge;
    }
    public int get_Etat_Reservoir(){
        return this.etat_reservoir;
    }
    
    public Case get_Position(){
        return this.Position;
    }
    public void set_Position(Case coordonee){
        this.Position = coordonee;
    }


    public int Deverser_Eau(int vol){
        if(this.change_etat_reservoir(vol) == -1){
            return -1;
        }
        else{
            return 0;
        }
    }

    public int change_etat_reservoir(int vol){
        if(vol > this.etat_reservoir){
            return -1;
        }
        else{
            this.etat_reservoir = this.etat_reservoir - vol;
            return 0;
        }
    }
}



