import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import gui.GUISimulator;
import java.awt.Color;

public class Main {
    public static void main(String[] args) {

        String fichierDonnees = "cartes/carteSujet.map" ;// Remplacez par le chemin du fichier de données.
        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
        try {
            // Charger les données de simulation depuis le fichier
            DonneesSimulation donneesSimulation = LecteurDonnees.lire(fichierDonnees);

            // Afficher les données de la carte
            System.out.println("== Affichage de la carte ==");
            donneesSimulation.afficherCarte(gui);

        } catch (FileNotFoundException e) {
            System.out.println("Erreur : Fichier de données non trouvé.");
        } catch (DataFormatException e) {
            System.out.println("Erreur de format des données : " + e.getMessage());
        }
    }
}