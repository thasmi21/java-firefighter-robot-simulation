import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import gui.GUISimulator;

import java.awt.Color;
import simulation.*;

public class Main {
    public static void main(String[] args) {
        String fichierDonnees = "cartes/carteSujet.map"; // Chemin du fichier de données.
        GUISimulator gui = new GUISimulator(900, 700, Color.BLACK);
        
        try {
            DonneesSimulation donneesSimulation = LecteurDonnees.lire(fichierDonnees);
            Simulateur sim = new Simulateur(gui, donneesSimulation, fichierDonnees);
            System.out.println(donneesSimulation.getWaterTiles());

            ChefPompier chef = new ChefPompier(donneesSimulation.getCarte(), donneesSimulation.getIncendies(), donneesSimulation.getRbotos(), sim);
            sim.setChefPompier(chef); // Injecter ChefPompier dans Simulateur

            
            chef.resolution(); // Lancement de la résolution initiale

        } catch (FileNotFoundException e) {
            System.out.println("Erreur : Fichier de données non trouvé.");
        } catch (DataFormatException e) {
            System.out.println("Erreur de format des données : " + e.getMessage());
        }
    }
}
