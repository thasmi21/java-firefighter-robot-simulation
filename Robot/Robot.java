

public abstract class Robot{
    private int vitesse_max;            // vitesse de déplacement max du robot (km/h)
    private int vitesse_de_base ;           // vitesse de déplacement du robot lue dans le fichier (km/h)
    private int capacite_reservoir;      // capacité max du reservoir (enL)
    private int temps_recharge;          // temps pour recharger completement le reservoir (s)
    private int decrementerReservoir;      // quantité déversée pour une extinction (en L)
    private int temps_decharge;           // temps qu'il faut pour verser la quantité d'eau pour une extinction (en s)
    private int etat_reservoir;          //  etat actuel du reservoir entre 0 et capacité reservoir (en L)
    private Case position;

    private NatureTerrain terrainsAcc[];


    public Robot(int vitesse_max,int vitesse_de_base, int capacite_reservoir,int temps_recharge,int decrementerReservoir,int temps_decharge,int etat_reservoir,Case position,NatureTerrain terrainsAcc[]){
        this.vitesse_max = vitesse_max;
        this.vitesse_de_base = vitesse_de_base;
        this.capacite_reservoir = capacite_reservoir;
        this.temps_recharge = temps_recharge;
        this.decrementerReservoir = decrementerReservoir;
        this.temps_decharge = temps_decharge;
        this.etat_reservoir = etat_reservoir;
        this.position = position;
        this.terrainsAcc = terrainsAcc;
    }

    /////////// getter ///////////
    /** Renvoie la vitesse maximum du robot*/
    public int getVitesseMax(){
        return this.vitesse_max;
    }

    /** Renvoie la vitesse du robot lue dans le fichier de données*/
    public int getVitesseBase(){
        return this.vitesse_de_base;
    }

    /** Renvoie la Capacité du reservoir*/
    public int getCapaciteReservoir(){
        return this.capacite_reservoir;
    }

    /** Renvoie le temps de rechargement du réservoir complet du Robot*/
    public int getTempsRecharge(){
        return this.temps_recharge;
    }

    /** Renvoie la quantité déversée pour une extinction */
    public int getDechargeReservoir(){
        return this.decrementerReservoir;
    }

    /** Renvoie le temps de dechargement du réservoir pour une opération unitaire de deversement*/
    public int getTempsDecharge(){
        return this.temps_decharge;
    }

    /** Renvoie le volume d'eau dans le reservoir*/
    public int getEtatReservoir(){
        return this.etat_reservoir;
    }
    
    /** Renvoie la position du robot*/
    public Case getPosition(){
        return this.position;
    }

    /** Renvoie la liste des terrains accessibles pour le robot*/
    public NatureTerrain[] getTerrainAcc(){
        return this.terrainsAcc;
    }

    /** Renvoie la vitesse du robot sur le terrain "<br> actu <br>"
    * @param actu position actuelle du robot
    */
    public abstract double getVitesse(NatureTerrain actu);

    /////////// autre methodes ///////////

    /** methode pour dire si la case coordonnee est accessible au robot
     * @param coordonnee :case a verifirer */
    private boolean isTerrainAccessible(Case coordonnee) {
        NatureTerrain terr[]= this.getTerrainAcc();
        for(int i =0 ; i<terr.length; i++){
            if (terr[i] == coordonnee.get_nature()) {
                return true; 
            }
        }
        return false; 
    }

    /** methode pour deplacer le robot sur la case coordonnee
     * @param coordonnee :case ou deplacer le robot
     * @exception RobotIllegalPosException Lève une exception RobotIllegalPosException si la case est inaccessible pour le robot */
    public void setPosition(Case coordonnee)throws RobotIllegalPosException{
        if (isTerrainAccessible(coordonnee)) {
            this.position = coordonnee;
        } else {
            throw new RobotIllegalPosException("la position n'est pas accessible pour le robot\n");
        }
    }

    /** réalise une operation unitaire de deversage ( cf documentation pour le volume deduit du reservoir)
    *  @exception RobotIllegalVolException Lève une exception RobotIllegalVolException si le volume <br>vol<br> est superieure au volume actuel du reservoir ou si le volume est negatif */
    public abstract void deverserEau()throws RobotIllegalVolException;

    /** methode pour remplir le reservoir **/
    public  void rechargerReservoir(){
        this.etat_reservoir = this.capacite_reservoir;
    }
    
    /** methode pour deduir la quantité vol du réservoir
     * @param vol : volume a déduire du réservoir
     * @exception RobotIllegalVolException Lève une exception RobotIllegalVolException si le volume <br>vol<br> est superieure au volume actuel du reservoir ou si le volume est negatif */
    public void setReservoir(int vol)throws RobotIllegalVolException{
        if(vol > this.etat_reservoir || vol<0) throw new RobotIllegalVolException("le volume "+ vol + "est superieur à la capacité du reservoir  ou negatif!\n");

        this.etat_reservoir = etat_reservoir - vol;
    }

    
    //public Chemin calculChemin(case case_finale){}
}



