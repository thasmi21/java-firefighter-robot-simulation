import java.util.ArrayList;

/**
 * Cette classe <b>définit les chemins</b> en associant le déplacement d'un robot sur une case avec un système de date permettant 
 * l'implémentation d'une notion de chronologie dans le traitement des incendies.
 */

public class Chemin{

    private  ArrayList<Case> chemin; //Tableau des cases que le robot doit parcourir pour arriver à l'incendie visé
    private  ArrayList<Double> dates; //Tableau des dates dindice i auxquelles le robot va passer sur la case de même indice
    private int longueurChemin; //Nombre de cases à parcourir 
    private int tempsTrajet; //Calcul du temps de trajet en prenant en compte la nature des terrains à franchir et les restrictions du robot concerné

    public Chemin(ArrayList<Case> chemin,  ArrayList<Double> dates, int longueurChemin, int tempsTrajet){
        this.chemin = chemin;
        this.dates = dates;
        this.longueurChemin = longueurChemin;
        this.tempsTrajet = tempsTrajet;
    }


    /* GETTERS **/

    public ArrayList<Case> getChemin(){
        return this.chemin;
    }

    public ArrayList<Double>  getDates(){
        return this.dates;
    }

    public int getlongueurChemin(){
        return this.longueurChemin;
    }

    public int getTempsTrajet(){
        return this.tempsTrajet;
    }


    //   utile ?

    public Case getIemeCase(int i) throws IndexOutOfBoundsException{
        if (i<0 || i>=this.chemin.size()){
            throw new IndexOutOfBoundsException("L'index i renseigné est en dehors des limites du tableau Cases !\n");
        }
        return this.chemin.get(i);
    }
    
     public Double getIemeDate(int i) throws IndexOutOfBoundsException
    {
        if (i<0 || i>=this.chemin.size()){
            throw new IndexOutOfBoundsException("L'index i renseigné est en dehors des limites du tableau Cases !\n");
        }
        return this.dates.get(i);
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

        for (int i = 0; i < chemin.size(); i++) {
            sb = sb + "etape " + i +" case " + chemin.get(i) + "\n";
        }

        sb = sb + "longueurChemin = " + longueurChemin;

        return sb.toString();
    }

}