package map;
import java.util.ArrayList;

/**
 * Cette classe <b>définit les chemins</b> en associant le déplacement d'un robot sur une case avec un système de date permettant 
 * l'implémentation d'une notion de chronologie dans le traitement des incendies.
 */

public class Chemin{

    private  ArrayList<Case> chemin; //Tableau des cases que le robot doit parcourir pour arriver à l'incendie visé
    private  ArrayList<Long> dates; //Tableau des dates dindice i auxquelles le robot va passer sur la case de même indice
    private int longueurChemin; //Nombre de cases à parcourir 
    private long tempsTrajet; //Calcul du temps de trajet en prenant en compte la nature des terrains à franchir et les restrictions du robot concerné

    public Chemin(ArrayList<Case> chemin,  ArrayList<Long> dates, int longueurChemin, long tempsTrajet){
        this.chemin = chemin;
        this.dates = dates;
        this.longueurChemin = longueurChemin;
        this.tempsTrajet = tempsTrajet;
    }


    /* GETTERS **/

    public ArrayList<Case> getChemin(){
        return this.chemin;
    }

    public ArrayList<Long>  getDates(){
        return this.dates;
    }

    public int getlongueurChemin(){
        return this.longueurChemin;
    }

    public long getTempsTrajet(){
        return this.tempsTrajet;
    }

    /* SETTERS **/

    public void addCase(Case Case) {
        this.chemin.add(Case);
    }
    
    public void removeFirstCase() {
        this.chemin.remove(0);
    }

    @Override
    public String toString() {
        String sb = "";
        String tm = "";
        for (int i = 0; i < this.chemin.size(); i++) {
            sb = sb + "etape " + i +" case " + this.chemin.get(i) + "\n";
        }
        for (int i = 0; i < this.dates.size(); i++) {
            tm = tm + "et le temps de parcours " + this.dates.get(i) + "\n";
        }

            
        sb = sb + "longueurChemin = " + this.longueurChemin +"et le temps total " + this.tempsTrajet + "\n";

        return (sb + tm).toString();
    }

}