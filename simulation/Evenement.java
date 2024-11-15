package simulation;
public abstract class Evenement implements Comparable<Evenement> {
    
    private long date; // Date en secondes à laquelle l'événement doit être exécuté

    /** Construit un événement à l'aide d'une <b>date qui fixe sa priorité</b> 
     * @param date Date à laquelle l'événement doit être exécuté
    */
    public Evenement(long date) {
        this.date = date;
    }

    /** Renvoie la <b>date</b> à laquelle l'événement doit être exécuté */
    public long getDate() {
        return this.date;
    }

    /** Renvoie l'objet DonneesSimulation associé à la simulation actuelle */

    /** <b>Effectue la ou les opérations associées</b> à l'exécution de l'événement dans la simulation */
    public abstract void execute();

    /** Permet de <b>comparer deux événements en terme de priorité (dates)</b>. Au passage on se permet même de déterminer une "distance" entre ces deux événements en soustrayant leur dates respectives 
     * @param e Événement à comparer à l'événement actuel
    */
    public int compareTo(Evenement e) {
        return (int) (this.date - e.getDate()); // La troncature en int n'est pas grave car les événements ne seront jamais trop espacés à priori
    }

}
