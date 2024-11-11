import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public abstract class Robot{
    private String type;
    private double vitesse_max;            // vitesse de déplacement max du robot (km/h)
    private double vitesse_de_base ;           // vitesse de déplacement du robot lue dans le fichier (km/h)
    private int capacite_reservoir;      // capacité max du reservoir (enL)
    private int temps_recharge;          // temps pour recharger completement le reservoir (s)
    private int decrementerReservoir;      // quantité déversée pour une extinction (en L)
    private int temps_decharge;           // temps qu'il faut pour verser la quantité d'eau pour une extinction (en s)
    private int etat_reservoir;          //  etat actuel du reservoir entre 0 et capacité reservoir (en L)
    private Case position;

    private NatureTerrain terrainsAcc[];


    public Robot(String type,double vitesse_max,double vitesse_de_base, int capacite_reservoir,int temps_recharge,int decrementerReservoir,int temps_decharge,int etat_reservoir,Case position,NatureTerrain terrainsAcc[]){
        this.type = type;
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
    public String getType(){
        return this.type;
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

    /*     public Chemin trouverChemin(Carte carte, Case depart, Case cible) {
        Map<Case, Double> tempsArrivee = new HashMap<>();
        Map<Case, Case> precedents = new HashMap<>();
        PriorityQueue<Case> queue = new PriorityQueue<>(Comparator.comparingDouble(tempsArrivee::get));
        
        tempsArrivee.put(depart, 0.0);
        queue.add(depart);
        
        while (!queue.isEmpty()) {
            Case current = queue.poll();
            System.out.println("Exploring case: " + current);  // Debug
    
            if (current.equals(cible)) break;
    
            // Ajout des voisins avec vérification de null
            Case[] voisins = new Case[4];
            try { voisins[0] = carte.getVoisin(current, Direction.NORD); } catch(CarteHorsCarteException e) { voisins[0] = null; }
            try { voisins[1] = carte.getVoisin(current, Direction.SUD); } catch(CarteHorsCarteException e) { voisins[1] = null; }
            try { voisins[2] = carte.getVoisin(current, Direction.EST); } catch(CarteHorsCarteException e) { voisins[2] = null; }
            try { voisins[3] = carte.getVoisin(current, Direction.OUEST); } catch(CarteHorsCarteException e) { voisins[3] = null; }
    
            for (Case voisin : voisins) {
                if (voisin != null) {
                    double vitesse = this.getVitesse(voisin.getNature());
                    double tempsDeplacement = carte.getTailleCases()/ vitesse;
                    double nouveauTemps = tempsArrivee.get(current) + tempsDeplacement;
    
                    if (nouveauTemps < tempsArrivee.getOrDefault(voisin, Double.MAX_VALUE)) {
                        tempsArrivee.put(voisin, nouveauTemps);
                        precedents.put(voisin, current);
                        queue.add(voisin);
                        //System.out.println("Added voisin: " + voisin + " with new time: " + nouveauTemps);  // Debug
                    }
                }
            }
        }
    
    
        // Reconstruction du chemin
        ArrayList<Case> route = new ArrayList<>();
        Case at = cible;int
        //System.out.println(precedents.keySet());
        while(at !=  null){
            route.add(at);
            System.out.println("Reconstructing path: added case " + at);  // Debug
            at = precedents.get(at);
        }
        Collections.reverse(route);
    
        Chemin chemin = new Chemin(route, null, route.size(), tempsArrivee.getOrDefault(cible, -1.0).intValue());
        return chemin;
    }*/

    public double[][] matrice_adj(Carte carte,Robot robot){
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
                        double vitesse1 = robot.getVitesse(voisins[p].getNature());
                        double vitesse2 = robot.getVitesse(current.getNature());
                        double tempsDeplacement = (carte.getTailleCases()/(2*vitesse1)) + (carte.getTailleCases()/(2*vitesse2));
                        graph[nodeIndex][neighborIndex] = tempsDeplacement;
                        graph[neighborIndex][nodeIndex] = tempsDeplacement;
                    }
                }

            } catch (CarteHorsCarteException e) {
                // Ignorer les cases hors de la carte
            }
        }
    }

    // Afficher la matrice d'adjacence
    System.out.println("Matrice d'adjacence :");
    for (int i = 0; i < col * lig; i++) {
        System.out.print("[");
        for (int k = 0; k < col * lig; k++) {
            System.out.printf("%8.2f", graph[i][k]);
        }
        System.out.println("]");
    }

    return graph;
    }

    static final double INF = Double.POSITIVE_INFINITY;

    public double dijkstra(double[][] graph, int source, int destination) {
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

        printPath(previous, source, destination);
        return distance[destination];
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

    private static void printPath(int[] previous, int source, int destination) {
        if (destination == source) {
            System.out.print(source + " ");
            return;
        } else if (previous[destination] == -1) {
            System.out.println("No path found");
            return;
        }
        
        printPath(previous, source, previous[destination]);
        System.out.print(destination + " ");
    }
}
















