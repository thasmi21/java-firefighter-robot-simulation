import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import gui.GUISimulator;
import java.awt.Color;

public class Main {
    public static void main(String[] args) {

        String fichierDonnees = "cartes/test.map" ;// Remplacez par le chemin du fichier de données.
        GUISimulator gui = new GUISimulator(900, 700, Color.BLACK);
        try {
            // Charger les données de simulation depuis le fichier
            DonneesSimulation donneesSimulation = LecteurDonnees.lire(fichierDonnees);

            // Afficher les données de la carte
            System.out.println("== Affichage de la carte ==");
            donneesSimulation.afficherCarte(gui);

            System.out.println("== Affichage du chemin ==");
            double[][] matrice = new double[donneesSimulation.getCarte().getNbColonnes()*donneesSimulation.getCarte().getNbColonnes()][donneesSimulation.getCarte().getNbColonnes()*donneesSimulation.getCarte().getNbColonnes()];
            matrice = donneesSimulation.getRbotos().get(0).matrice_adj(donneesSimulation.getCarte(), donneesSimulation.getRbotos().get(0));
            double stat = donneesSimulation.getRbotos().get(0).dijkstra(matrice, 1, 8);

        } catch (FileNotFoundException e) {
            System.out.println("Erreur : Fichier de données non trouvé.");
        } catch (DataFormatException e) {
            System.out.println("Erreur de format des données : " + e.getMessage());
        }
        
    }
}