/**
 * Cette classe <b>définit la carte</b> et comporte les méthodes pour gérer dynamiquement les cases qui la composent.
 * Elle <b>contient des méthodes utiles en phase de debug</b> pour l'affichage pour un affichage simplifié en console notamment.
 * 
 * 
 */
public class Carte {

    private static int TAILLE_PAR_DEFAUT = 1;
 
    private Case cases[][];  // Tableau n x m de cases
    private int tailleCases; // Taille du côté des cases

    /** Construit la carte de cases <b>non attribuées</b> dans le format spécifié (la taille des cases de la carte est <b>la taille par défaut</b>)
     * @param nbLignes Nombre de lignes de la carte
     * @param nbColonnes Nombre de colonnes de la carte
    */
    public Carte(int nbLignes, int nbColonnes) { 
        this.tailleCases = TAILLE_PAR_DEFAUT;
        this.cases = new Case[nbLignes][nbColonnes];
    }

    /** Construit la carte de cases <b>non attribuées</b> dans le format spécifié 
     * @param nbLignes Nombre de lignes de la carte
     * @param nbColonnes Nombre de colonnes de la carte
     * @param tailleCases Taille des cases de la carte
    */
    public Carte(int nbLignes, int nbColonnes, int tailleCases) {
        this.tailleCases = tailleCases;
        this.cases = new Case[nbLignes][nbColonnes];
    }

    /** Construit la carte à partir d'<b>une copie</b> d'un tableau de cases déjà existant (la taille des cases de la carte est <b>la taille par défaut</b>)
     * @param case_tab Tableau de case d'origine
    */
    public Carte(Case case_tab[][]) { 
        this.tailleCases = TAILLE_PAR_DEFAUT;
        this.cases = new Case[case_tab.length][case_tab[0].length];

        for(int lig =  0; lig < case_tab.length; lig++)
            for(int col = 0; col < case_tab[0].length; col++)
                this.cases[lig][col] = new Case(lig, col, case_tab[lig][col].getNature());
    }

    /** Construit la carte à partir d'<b>une copie</b> d'un tableau de cases déjà existant
     * @param case_tab Tableau de cases d'origine
     * @param tailleCases Taille des cases de la carte
    */
    public Carte(Case case_tab[][], int tailleCases) { 
        this.tailleCases = tailleCases;
        this.cases = new Case[case_tab.length][case_tab[0].length];

        for(int lig =  0; lig < case_tab.length; lig++)
            for(int col = 0; col < case_tab[0].length; col++)
                this.cases[lig][col] = new Case(lig, col, case_tab[lig][col].getNature());
    }


    /* SETTERS */

    /** Permet de d'attribuer <b>une copie</b> de la case souhaitée à l'emplacement de cette case sur la carte s'il existe
     *  @param c   Case à attribuer à l'emplacement souhaité
     *  @exception CarteHorsCarteException Lève une exception CarteHorsCarteException si l'emplacement n'existe pas sur la carte
    */
    public void setCase(Case c) throws CarteHorsCarteException
    {
        int lig = c.getLigne();
        int col = c.getColonne();
        if(lig > this.cases.length || col > this.cases[0].length)
            throw new CarteHorsCarteException("La case de coordonnées ("+lig+";"+col+") n'existe pas !\n");

        this.cases[lig][col] = new Case(c.getLigne(), c.getColonne(), c.getNature());
    }

    /** Permet de d'attribuer à la case d'emplacement (lig,col) (<b>s'il existe</b>) sur la carte une case avec les paramètres spécifiés en argument
     *  @param lig Ligne de la case concernée
     *  @param col Colonne de la case concernée
     *  @param nature Nature du terrain de la case concernée
     *  @exception CarteHorsCarteException Lève une exception CarteHorsCarteException si l'emplacement n'existe pas sur la carte
    */
    public void setCase(int lig, int col, NatureTerrain nature) throws CarteHorsCarteException
    {
        if(lig > this.cases.length || col > this.cases[0].length)
            throw new CarteHorsCarteException("La case de coordonnées ("+lig+";"+col+") n'existe pas !\n");

        this.cases[lig][col] = new Case(lig, col, nature);
    }

    /* GETTERS */

    /** Renvoie le nombre de lignes de la carte */
    public int getNbLignes() {
        return this.cases.length;
    }

    /** Renvoie le nombre de colonnes de la carte */
    public int getNbColonnes() {
        return this.cases[0].length;
    }

    /** Renvoie la taille des cases de la carte */
    public int getTailleCases() {
        return this.tailleCases;
    }

    /** Permet de récupérer <b>une copie</b> de la case 
     * @param lig Ligne de la case concernée
     * @param col Colonne de la case concernée
     * @exception CarteHorsCarteException Lève une exception de type ArrayIndexOutOfBoundsException si la case concernée n'existe pas sur la carte
    */
    public Case getCase(int lig, int col) throws CarteHorsCarteException {
        if(lig > this.cases.length || col > this.cases[0].length)
            throw new CarteHorsCarteException("La case de coordonnées ("+lig+";"+col+") n'existe pas !\n");
        
        return this.cases[lig][col];
    }

    /** Permet de déterminer si une case voisine visée de puis une case d'origine dans une direction donnée existe sur la carte ou non.
     * Cette méthode renvoie <b>true</b> si elle existe et <b>false</b> sinon. 
     * @param src Case d'origine 
     *  @param dir Direction vers laquelle le voisin se situerait en partant de <b>src</b>
    */
    public boolean voisinExiste(Case src, Direction dir) {
        // On calcule la position de ce supposé voisin par rapport à la case source et la direction indiquée
        int lig_voisin_potentiel = (dir == Direction.NORD)  ? src.getLigne()   - 1 : (dir == Direction.SUD) ? src.getLigne()   + 1 : src.getLigne();
        int col_voisin_potentiel = (dir == Direction.OUEST) ? src.getColonne() - 1 : (dir == Direction.EST) ? src.getColonne() + 1 : src.getColonne();

        // Et on vérifie que cette case se retrouve bien dans sur la carte, i.e pas de débordement
        return (lig_voisin_potentiel >= 0) && (lig_voisin_potentiel < this.cases.length) && (col_voisin_potentiel >= 0) && (col_voisin_potentiel < this.cases[0].length);
    }

    /** @param src Case d'origine 
     *  @param dir Direction vers laquelle le voisin se situerait en partant de <b>src</b>
     *  @exception CarteHorsCarteException Lève une exception CarteHorsCarteException si la case du voisin n'existe pas sur la carte
    */
    public Case getVoisin(Case src, Direction dir) throws CarteHorsCarteException {
        if(!this.voisinExiste(src, dir)) throw new CarteHorsCarteException("La case de coordonnées ("+src.getLigne()+";"+src.getColonne()+") n'a pas de voisin dans la direction "+dir.toString()+" !\n");

        int lig_voisin = (dir == Direction.NORD)  ? src.getLigne()   - 1 : (dir == Direction.SUD) ? src.getLigne()   + 1 : src.getLigne();
        int col_voisin = (dir == Direction.OUEST) ? src.getColonne() - 1 : (dir == Direction.EST) ? src.getColonne() + 1 : src.getColonne();

        return this.cases[lig_voisin][col_voisin];
    }

    /** Permet l'<b>affichage graphique de la carte</b> au <b>format texte</b> sur la <b>sortie standard</b> (System.out) */
    public void afficheDebug() {
        int nbLignes = this.cases.length;
        int nbColonnes = this.cases[0].length;

        for(int x = 0; x < nbColonnes; x++) {
            System.out.print(" ____"); 
        }

        for(int y = 0; y < nbLignes; y++) {
            for(int i = 0; i < 3; i++) {
                System.out.print("\n|");
                for(int x = 0; x < nbColonnes; x++) {
                    switch(i) {
                        case 0:
                            System.out.print("    |");
                            break;
                        case 1:
                            System.out.print(" ");
                            switch(this.cases[y][x].getNature()) {
                                case TERRAIN_LIBRE:
                                    System.out.print("TL");
                                    break;
                                case ROCHE:
                                    System.out.print("Ro");
                                    break;
                                case EAU:
                                    System.out.print("Ea");
                                    break;
                                case FORET:
                                    System.out.print("Fo");
                                    break;
                                case HABITAT:
                                    System.out.print("Ha");
                                    break;
                                default: // Si la case n'est pas assignée
                                    System.out.print("~~");
                                    break;
                            }
                            System.out.print(" |");
                            break;
                        case 2:
                            System.out.print("____|");
                            break;
                    }
                }
            }
        }

        System.out.println("\n"); // On fait un double retour à la ligne
    }

    /** Renvoie une description textuelle générale de la carte */
    @Override public String toString() {
        return "Cette instance de carte possède "+this.cases.length+" lignes et "+this.cases[0].length+" colonnes. Chacune de ses cases ont une taille de "+this.tailleCases+"m.\n";
    }
}
