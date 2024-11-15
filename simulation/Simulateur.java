package simulation;
import java.util.Objects;
import java.util.PriorityQueue;

import gui.GUISimulator;
import gui.Simulable;

public class Simulateur implements Simulable {
    
    private long dateSimulation;         // Les date avancent par pas de 1 unité arbitraire de temps (une unité = 1)
    PriorityQueue<Evenement> evenements; // Liste des événements triés par ordre de priorité
    GUISimulator gui;                    // Référence de la GUI associée à la simulation
    private DonneesSimulation donneesSimulation;
    private String nomF;
    private ChefPompier chefPompier;



    /** Construit le simulateur, <b>initialise la date de début de simulation à 0</b> et lie une GUI à ce simulateur 
     * @param gui Référence vers une instance de GUISimulator déjà existante
    */
    public Simulateur(GUISimulator gui,DonneesSimulation donneesSimulation,String nomF) {
        this.dateSimulation = 0;
        this.evenements = new PriorityQueue<Evenement>();
        this.gui = gui; // Passage par référence à une GUI déjà existante
        gui.setSimulable(this);
        this.donneesSimulation = donneesSimulation;
        this.nomF = nomF;
    }


    
    public void setChefPompier(ChefPompier chef) {
        this.chefPompier = chef;
    }

    public ChefPompier getChefPompier() {
        return this.chefPompier;
    }

    public DonneesSimulation getDonneesSimulation() {
        return donneesSimulation;
    }

    public long getDateSimulation(){
        return dateSimulation;
    }

    /** Ajoute l'événement e dans la file de priorité 
     * @param e Événement à ajouter dans la file
    */
    public void addEvenement(Evenement e){
        this.evenements.add(e);
    }

    /** <b>Incrémente la date de simulation de 1</b> */
    public void incrementeDate() {
        this.dateSimulation++;
    }

    /** <b>Renvoie true si la simulation est terminée</b>, i.e si la date n'est pas la date initiale <b>et</b> si il n'y a plus d'événements */
    public boolean simulationTerminee() {
        return this.dateSimulation > 0 && this.evenements.isEmpty();
    }
    
    /** Gère la prochaine étape de simulation : <b>incrémentation de la date de simulation</b>, <b>traitement des événements</b> dans l'ordre des dates, vérification de fin de simulation, etc. */
    
    public void next()
    {
        this.incrementeDate();
        //long date_evt = -1; // Date de l'événement courant (pour vérifier si on doit l'exécuter et si on doit continuer le parcours de la file)
        Evenement curr_evt = null;
        while((curr_evt = this.evenements.peek()) != null && curr_evt.getDate() <= this.dateSimulation){
            curr_evt.execute();     // On effectue les opérations associées à l'événement
            this.evenements.poll(); // On retire l'événement de la file
        }

        if(this.simulationTerminee()){
        }

        donneesSimulation.afficherCarte(gui);
    }

    /** On redémarre la simulation en effaçant tout */
    public void restart() {
        this.dateSimulation = 0;
        this.evenements.clear();
        try {
            this.donneesSimulation = LecteurDonnees.lire(nomF);
            this.donneesSimulation.afficherCarte(gui);
            this.chefPompier = new ChefPompier(this);
        } catch (Exception e) {}
    }

    @Override
    public String toString(){
        return "Ce simulateur possède "+this.evenements.size()+" événement(s) en attente. Il est actuellement à la date : "+this.dateSimulation;
    }

    /** Génère un code de hashage pour une instance de la classe */
    @Override public int hashCode() {
        return Objects.hash(this.dateSimulation, this.evenements.hashCode(), this.gui.hashCode(), this.donneesSimulation.hashCode(), this.nomF.hashCode(), this.chefPompier.hashCode());
    }
}

