package robots;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

import map.*;
import simulation.DeplacerRobotEvenement;
import simulation.DeverserEauRobotEvenement;
import simulation.Evenement;
import exceptions.*;
import simulation.*;


public abstract class Robot{
    private String type;
    private Etat state;
    private double vitesse_max;       // Vitesse de déplacement max du robot (km/h)
    private double vitesse_de_base ;  // Vitesse de déplacement du robot lue dans le fichier (km/h)
    private int capacite_reservoir;   // Capacité max du reservoir (en L)
    private int temps_recharge;       // Temps pour recharger completement le reservoir (s)
    private int decremente_reservoir; // Quantité déversée pour une extinction (en L)
    private int temps_decharge;       // Temps qu'il faut pour verser la quantité d'eau pour une extinction (en s)
    private int etat_reservoir;       // Etat actuel du reservoir entre 0 et capacité reservoir (en L)
    private Case position;            // Position courante du robot

    private NatureTerrain terrainsAcc[];


    public Robot(String type,Etat state,double vitesse_max,double vitesse_de_base, int capacite_reservoir,int temps_recharge,int decremente_reservoir,int temps_decharge,int etat_reservoir,Case position,NatureTerrain terrainsAcc[]){
        this.type = type;
        this.vitesse_max = vitesse_max;
        this.vitesse_de_base = vitesse_de_base;
        this.capacite_reservoir = capacite_reservoir;
        this.temps_recharge = temps_recharge;
        this.decremente_reservoir = decremente_reservoir;
        this.temps_decharge = temps_decharge;
        this.etat_reservoir = etat_reservoir;
        this.position = position;
        this.terrainsAcc = terrainsAcc;
    }

    /////////// getter ///////////

    /** Renvoie la vitesse maximum du robot*/
    public String getType(){
        return this.type;
    }

    public Etat getEtat(){
        return this.state;
    }
    /** Renvoie la vitesse maximum du robot*/
    public double getVitesseMax(){
        return this.vitesse_max;
    }

    /** Renvoie la vitesse du robot lue dans le fichier de données*/
    public double getVitesseBase(){
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
        return this.decremente_reservoir;
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


    public void setEtat(Etat etat){
        this.state = etat;
    }
    /** methode pour dire si la case coordonnee est accessible au robot
     * @param coordonnee :case a verifirer */
    private boolean isTerrainAccessible(Case coordonnee) {
        NatureTerrain terr[]= this.getTerrainAcc();
        for(int i =0 ; i<terr.length; i++){
            if (terr[i] == coordonnee.getNature()) {
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
            throw new RobotIllegalPosException("la case "+coordonnee.toString()+" n'est pas accessible pour le robot\n");
        }
    }

    /** Réalise une opération unitaire de déversage (cf documentation pour le volume deduit du reservoir) et renvoie la quantité d'eau <b>déversée</b>*/
    public int deverserEau() {
        try {
            int etat_reservoir = this.getEtatReservoir();
            int decharge_reservoir = this.getDechargeReservoir();
            // On calcule le volume d'eau à déverser en fonction de l'état actuel du réservoir
            int volume_deversement = (decharge_reservoir < etat_reservoir) ? etat_reservoir - decharge_reservoir : etat_reservoir;
            this.setReservoir(volume_deversement); // On déverse finalement l'eau sur la case courante (attention: l'état d'un incendie présent sur cette case ne sera pas actulisé ici)
            return volume_deversement; // On oublie pas de renvoyer le volume d'eau déversé
        } catch(RobotIllegalVolException e) {
            System.out.println(e);
            return 0; // En cas d'erreur le robot n'a déversé aucun litre d'eau
        }
    }

    /** Méthode pour remplir le reservoir **/
    public  void rechargerReservoir() {
        this.etat_reservoir = this.capacite_reservoir;
    }
    
    /** Méthode pour déduire la quantité vol du réservoir
     * @param vol Volume à déduire du réservoir
     * @exception RobotIllegalVolException Lève une exception RobotIllegalVolException si le volume <br>vol<br> est superieure au volume actuel du reservoir ou si le volume est negatif */
    public void setReservoir(int vol)throws RobotIllegalVolException{
        if(vol > this.etat_reservoir || vol<0) throw new RobotIllegalVolException("le volume "+ vol + "est superieur à la capacité du reservoir  ou negatif!\n");

        this.etat_reservoir = etat_reservoir - vol;
    }

    
    //Plus court Chemin//

    static final double INF = Double.POSITIVE_INFINITY;

    public Chemin dijkstra(Carte carte, Case fin) {
        double[][] graph = matrice_adj(carte);
        int source =  this.position.getLigne() * carte.getNbColonnes() + this.position.getColonne();
        int destination = fin.getLigne() * carte.getNbColonnes() + fin.getColonne();
        int n = graph.length;
        double[] distance = new double[n];
        boolean[] visited = new boolean[n];
        int[] previous = new int[n];  // Pour garder une trace du chemin

        Arrays.fill(distance, INF);
        Arrays.fill(previous, -1);
        distance[source] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = minDistance(distance, visited);
            if (u == -1) break; // Aucun nœud accessible restant
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != INF && distance[u] != INF &&
                        distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                    previous[v] = u;  // Enregistrer le prédécesseur pour le chemin
                }
            }
        }
        Chemin chemin = makechemin(carte,buildPath(previous, source, destination),graph);
        return chemin;
    }
    private double[][] matrice_adj(Carte carte){
        int col= carte.getNbColonnes();
        int lig = carte.getNbLignes();
        double[][] graph = new double[col*lig][col*lig];

        for (int i = 0; i < col * lig; i++) {
            for (int j = 0; j < col * lig; j++) {
                graph[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        for(int i = 0; i< col; i++){
            for(int k= 0; k<lig; k++){
                
                try { Case current = carte.getCase(i, k);
                int nodeIndex = i * col + k;

                Case[] voisins = new Case[4];
                try { voisins[0] = carte.getVoisin(current, Direction.NORD); } catch(CarteHorsCarteException e) { voisins[0] = null; }
                try { voisins[1] = carte.getVoisin(current, Direction.SUD); } catch(CarteHorsCarteException e) { voisins[1] = null; }
                try { voisins[2] = carte.getVoisin(current, Direction.EST); } catch(CarteHorsCarteException e) { voisins[2] = null; }
                try { voisins[3] = carte.getVoisin(current, Direction.OUEST); } catch(CarteHorsCarteException e) { voisins[3] = null; }
    
                for (int p = 0; p < 4; p++) { 
                    if (voisins[p] != null) {
                        int neighborIndex = voisins[p].getLigne() * col + voisins[p].getColonne();
                        double vitesse1 = this.getVitesse(voisins[p].getNature());
                        double vitesse2 = this.getVitesse(current.getNature());
                        double tempsDeplacement = (carte.getTailleCases()/(2*vitesse1)) + (carte.getTailleCases()/(2*vitesse2));
                        graph[nodeIndex][neighborIndex] = tempsDeplacement;
                        graph[neighborIndex][nodeIndex] = tempsDeplacement;
                    }
                }

            } catch (CarteHorsCarteException e) {}
        }
    
    }
    return graph;
    }

    private static int minDistance(double[] distance, boolean[] visited) {
        double min = INF;
        int minIndex = -1;
        for (int v = 0; v < distance.length; v++) {
            if (!visited[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }
        }
        return minIndex;
    }


    private static ArrayList<Integer> buildPath(int[] previous, int source, int destination) {
        ArrayList<Integer> path = new ArrayList<>();
        
        // On part de la destination et on remonte jusqu'à la source
        for (int at = destination; at != -1; at = previous[at]) {
            path.add(at);
            if (at == source) break;
        }
        
        // Si le dernier élément ajouté n'est pas la source, cela signifie qu'il n'y a pas de chemin
        if (path.get(path.size() - 1) != source) {
            System.out.println("No path found");
            return new ArrayList<>(); // Retourne un chemin vide si aucun chemin trouvé
        }
        
        // Inverser la liste pour obtenir le chemin dans le bon ordre (de source à destination)
        Collections.reverse(path);
        return path;
    }

    private Chemin makechemin(Carte carte, ArrayList<Integer> path, double[][] graph) {
        ArrayList<Case> route = new ArrayList<>(); 
        ArrayList<Long> temps = new ArrayList<>(); 
        long tempsTotal = 0;
    
        for (int k = 0; k < path.size(); k++) {
            int node = path.get(k);
            int i = node / carte.getNbColonnes();
            int j = node % carte.getNbColonnes();
    
            try {
                route.add(carte.getCase(i, j));
                
                // Ajouter le temps de trajet entre cases consécutives et l'ajouter au temps total
                if (k > 0) {  // Ignorer le premier élément car il n'a pas de précédent
                    int prevNode = path.get(k - 1);
                    double tempsDeplacement = graph[prevNode][node];
                    long t = (long) Math.floor(tempsDeplacement);
                    temps.add(t);
                    tempsTotal += tempsDeplacement;  // Accumuler le temps de déplacement
                }
            } catch (Exception e) {
                System.out.println("Erreur lors de l'accès à la case (" + i + ", " + j + "): " + e.getMessage());
            }
        }
    
        // Créer et retourner un objet Chemin avec la route, les temps de trajet, et le temps total
        Chemin chemin = new Chemin(route, temps, route.size(), tempsTotal);
        return chemin;
    }

    public void mkevent(Incendie incendie, Chemin chem,PriorityQueue<Evenement> evenements, Simulateur sim){
        while(chem.getChemin().size() !=0){
            Case actuelle = chem.getChemin().remove(0);
            long date = chem.getDates().remove(0);
            DeplacerRobotEvenement e = new DeplacerRobotEvenement(this, actuelle, sim.getDateSimutaion() + date);
            evenements.add(e);
        }
        DeverserEauRobotEvenement deverser = new DeverserEauRobotEvenement(this,datecourant + this.getTempsDecharge());
        //changerEtatEvenement e ) = new (this,datecourant + this.getTempsDecharge());
    }
}
















