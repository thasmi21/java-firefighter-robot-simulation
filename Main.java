import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import gui.GUISimulator;
import java.awt.Color;
import map.*;
import robots.*;
import exceptions.*;

public class Main {
    public static void main(String[] args) {

        String fichierDonnees = "cartes/carteSujet.map" ;// Remplacez par le chemin du fichier de données.
        GUISimulator gui = new GUISimulator(900, 700, Color.BLACK);
        try {
            // Charger les données de simulation depuis le fichier
            DonneesSimulation donneesSimulation = LecteurDonnees.lire(fichierDonnees);

            // Afficher les données de la carte
            System.out.println("== Affichage de la carte ==");
            donneesSimulation.afficherCarte(gui);

            System.out.println("== Affichage du chemin ==");
            try {
               Case c = donneesSimulation.getCarte().getCase(6, 1);
               System.out.println(donneesSimulation.getRbotos().get(1).dijkstra(donneesSimulation.getCarte(),c));
            } catch (CarteHorsCarteException e) {}

        } catch (FileNotFoundException e) {
            System.out.println("Erreur : Fichier de données non trouvé.");
        } catch (DataFormatException e) {
            System.out.println("Erreur de format des données : " + e.getMessage());
        }
        
    }
}