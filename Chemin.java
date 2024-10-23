/**
 * Cette classe <b>définit les chemins</b> en associant le déplacement d'un robot sur une case avec un système de date permettant 
 * l'implémentation d'une notion de chronologie dans le traitement des incendies.
 */

public class Chemin{

    private Case cases[]; //Tableau des cases que le robot doit parcourir pour arriver à l'incendie visé
    private long dates[]; //Tableau des dates dindice i auxquelles le robot va passer sur la case de même indice
    private int longueurChemin; //Nombre de cases à parcourir 
    private int tempsTrajet; //Calcul du temps de trajet en prenant en compte la nature des terrains à franchir et les restrictions du robot concerné

    public Chemin(Case newCases[], long newDates[], int newLongueurChemin, int newTempsTrajet){
        this.cases[] = newCases[];
        this.dates[] = newDates[];
        this.longeurChemin = newLongueurChemin;
        this.tempsTrajet = newTempsTrajet;
    }

    /* SETTERS **/

    public setCases(Case newCases[]){
        this.cases[] = newCases[];
    }

    /** Modifie le tableau des cases à parcourir pour remplacer la case à l'index i avec celle rentrée en argument
     * @param case Nouvelle case sur laquelle le robot doit passer
     * @param i index de la case à remplacer dans le tableau
     * @exception IndexOutOfRangeException Lève une exception IndexOutOfRangeException si l'index i ne correspond à aucun emplacement de case dans le tableau Cases
    */
    public void setUneCase(Case case, int i) throws IndexOutOfRangeException
    {
        if (i<0 || i>this.cases[].length){
            throw new IndexOutOfRangeException("L'index i renseigné est en dehors des limites du tableau Cases !\n");
        }
        this.cases[i] = case;
    }

    /** Modifie le tableau des cases à parcourir pour ajouter la case rentrée en argument à l'index i
     * @param case Nouvelle case sur laquelle le robot doit passer
     * @param i index de la case à ajouter dans le tableau
     * @exception IndexOutOfRangeException Lève une exception IndexOutOfRangeException si l'index i ne correspond à aucun emplacement de case dans le tableau Cases
    */
    public void setNouvelleCase(Case case, int i) throws IndexOutOfRangeException
    {
        if (i<0 || i>this.cases[].length){
            throw new IndexOutOfRangeException("L'index i renseigné est en dehors des limites du tableau Cases !\n");
        }
        if (i == this.cases[].length - 1){
            this.cases[i] = case;}
        else {
            for (int j = this.cases[].length - 1; j>=i; j--){
                this.cases[j+1] = this.cases[j];
            }
            this.cases[i] = case;
        }
    }

    /** Modifie le tableau des cases à parcourir pour retirer la case rentrée en argument à l'index i
     * @param case Case sur laquelle le robot ne doit plus passer
     * @param i index de la case à retirer dans le tableau
     * @exception IndexOutOfRangeException Lève une exception IndexOutOfRangeException si l'index i ne correspond à aucun emplacement de case dans le tableau Cases
    */
    public void setRetirerCase(Case case, int i) throws IndexOutOfRangeException
    {
        if (i<0 || i>this.cases[].length){
            throw new IndexOutOfRangeException("L'index i renseigné est en dehors des limites du tableau Cases !\n");
        }
        if (i == this.cases[].length - 1){
            this.cases[i] = case;}
        else {
            for (int j = this.cases[].length - 1; j>=i; j--){
                this.cases[j+1] = this.cases[j];
            }
            this.cases[i] = case;
        }
    }

    /* GETTERS **/
    
    public Case getCases(){
        return this.cases[];
    }

    public Case getIemeCase(int i) throws IndexOutOfRangeException
    {
        if (i<0 || i>=this.cases[].length){
            throw new IndexOutOfRangeException("L'index i renseigné est en dehors des limites du tableau Cases !\n");
        }
        return this.cases[];
    }
    
    public long getDates(){
        return this.dates[i];
    }

     public long getIemeDate(int i) throws IndexOutOfRangeException
    {
        if (i<0 || i>=this.cases[].length){
            throw new IndexOutOfRangeException("L'index i renseigné est en dehors des limites du tableau Cases !\n");
        }
        return this.dates[i];
    }

    public int getlongueurChemin(){
        return this.longueurChemin;
    }

    public int getTempsTrajet(){
        return this.tempsTrajet;
    }
}