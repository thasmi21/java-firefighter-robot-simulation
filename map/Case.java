package map;

import java.util.Objects;

public class Case{
    private int ligne;
    private int colonne;
    private NatureTerrain nature;

    public Case(int ligne, int colonne, NatureTerrain nature){
        this.ligne = ligne;
        this.colonne = colonne;
        this.nature = nature;
    }

    public int getLigne(){
        return this.ligne;
    }
    public int getColonne(){
        return this.colonne;
    }
    public NatureTerrain getNature(){
        return this.nature;
    }

    @Override
    public String toString() {
        return "[col : "  + this.colonne + " lig : " + this.ligne + "]" ;
    }

    public boolean equalsEAU() {
        return this.nature == NatureTerrain.EAU;

    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Vérifie si c'est le même objet en mémoire
        if (obj == null || getClass() != obj.getClass()) return false; // Vérifie le type de l'objet
        Case other = (Case) obj; 
        return this.ligne == other.ligne && this.colonne == other.colonne; // Compare les attributs `lig` et `col`
    }

    @Override
    public int hashCode() {
        return Objects.hash(ligne, colonne); // Génère un code de hachage basé sur `lig` et `col`
    }
}
