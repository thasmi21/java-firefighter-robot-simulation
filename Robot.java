public abstract class Robot{
    private int vitesse_max;            // vitesse de déplacement max du robot (km/h)
    private int vitesse_de_base ;           // vitesse de déplacement du robot lue dans le fichier (km/h)
    private int capacite_reservoir;      // capacité max du reservoir (enL)
    private int temps_recharge;          // temps pour recharger completement le reservoir (s)
    private int decharge_reservoir;      // quantité déversée pour une extinction (en L)
    private int temps_decharge;           // temps qu'il faut pour verser la quantité d'eau pour une extinction (en s)
    private int etat_reservoir;          //  etat actuel du reservoir entre 0 et capacité reservoir (en L)
    private Case position;

    private NatureTerrain terrainsAcc[];


    public Robot(int vitesse_max,int vitesse_de_base, int capacite_reservoir,int temps_recharge,int decharge_reservoir,int temps_decharge,int etat_reservoir,Case position,NatureTerrain terrainsAcc[]){
        this.vitesse_max = vitesse_max;
        this.vitesse_de_base = vitesse_de_base;
        this.capacite_reservoir = capacite_reservoir;
        this.temps_recharge = temps_recharge;
        this.decharge_reservoir = decharge_reservoir;
        this.temps_decharge = temps_decharge;
        this.etat_reservoir = etat_reservoir;
        this.position = position;
        this.terrainsAcc = terrainsAcc;
    }
    // getter
    public int getVitesseMax(){
        return this.vitesse_max;
    }
    public int getVitesseBase(){
        return this.vitesse_de_base;
    }
    public int getCapaciteReservoir(){
        return this.capacite_reservoir;
    }
    public int getTempsRecharge(){
        return this.temps_recharge;
    }
    public int getDechargeReservoir(){
        return this.decharge_reservoir;
    }
    public int getTempsDecharge(){
        return this.temps_decharge;
    }
    public int getEtatReservoir(){
        return this.etat_reservoir;
    }
    
    public Case getPosition(){
        return this.position;
    }
    public NatureTerrain[] getTerrainAcc(){
        return this.terrainsAcc;
    }

    public int getTempsTrajet(Case trajet[]){
        for(int i=0 ; i< trajet.length ;i++){
            trajet[i].
        }

    }
    //////////////


    public boolean isTerrainAccessible(Case coordonnee) {
        NatureTerrain terr[]= this.getTerrainAcc();
        for(int i =0 ; i<terr.length; i++){
            if (terr[i] == coordonnee.get_nature()) {
                return true;  // Le terrain est accessible
            }
        }
        return false;  // Terrain non accessible
    }

    public void setPosition(Case coordonnee) {
        if (isTerrainAccessible(coordonnee)) {
            // Met à jour la position si le terrain est accessible
            this.position = coordonnee;
        } else {
            System.out.println("Terrain inaccessible pour le Drone.");
        }
    }

    public int deverserEau(int vol){
        if(this.changeEtatReservoir(vol) == -1){
            return -1;
        }
        else{
            try {
                // Le robot attend pendant 'temps_decharge' secondes
                Thread.sleep(this.temps_decharge * 1000);  // Conversion en millisecondes
            } catch (InterruptedException e) {
                System.out.println("L'attente a été interrompue.");
                e.printStackTrace();
            }
            setFeuDecrement(vol);
            return 0;
        }
    }

    // modifi l'etat du reservoir et reduit le reservoir de vol si c'est possible sinon retourn -1
    public int changeEtatReservoir(int vol){
        if(vol > this.etat_reservoir){
            return -1;
        }
        else{
            this.etat_reservoir = this.etat_reservoir - vol;
            return 0;
        }
    }

    public void rechargerReservoir(){
        if(this.position.get_nature() == NatureTerrain.EAU ){
            try {
                // Le robot attend pendant 'temps_decharge' secondes
                Thread.sleep(this.temps_recharge * 1000);  // Conversion en millisecondes
            } catch (InterruptedException e) {
                System.out.println("L'attente a été interrompue.");
                e.printStackTrace();
            }
            this.etat_reservoir = this.capacite_reservoir;
        }
        else{
            //generer un erreur 
        }
    }
}



