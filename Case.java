public class Case{
    private int ligne;
    private int colonne;
    private NatureTerrain nature;

    public Case(int ligne, int colonne, NatureTerrain nature){
        this.ligne = ligne;
        this.colonne = colonne;
        this.nature = nature;
    }

    public int get_Ligne(){
        return this.ligne;
    }
    public int get_Colonne(){
        return this.colonne;
    }
    public NatureTerrain get_nature(){
        return this.nature;
    }
}
