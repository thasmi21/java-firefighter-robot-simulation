import gui.GUISimulator;
import gui.ImageElement;
//import gui.Rectangle;
import gui.Text;
import gui.Oval;
import java.awt.Color;
import java.util.ArrayList;

public class DonneesSimulation {
    private Carte carte;
    private ArrayList<Robot> robots;
    private ArrayList<Incendie> incendies;

    public DonneesSimulation(Carte carte, ArrayList<Incendie> incendies,ArrayList<Robot> robots) {
        this.carte = carte;
        this.robots = robots;
        this.incendies = incendies;
    }

    /**
     * Affiche la carte et les éléments de la simulation sur l'interface graphique.
     * @param gui L'interface graphique utilisée pour l'affichage.
     */
    public void afficherCarte(GUISimulator gui) {
        gui.reset(); // Supprime les anciens éléments

        // Dessin de la carte
        for (int i = 0; i < carte.getNbLignes(); i++) {
            for (int j = 0; j < carte.getNbColonnes(); j++) {
                try{
                Case c = carte.getCase(i, j);
                String image = obtenirCouleurNature(c.getNature());

                gui.addGraphicalElement(new ImageElement(j * 50 , i * 50,image,50,50,null));
                }
                catch(CarteHorsCarteException e){
                    System.err.println("erreur depassement création care donneesimulation");
                }
            }
        }

        // Dessin des incendies
        for (Incendie incendie : incendies) {
            int x = incendie.getPosition().getColonne() * 50 +25 ;
            int y = incendie.getPosition().getLigne() * 50 +25 ;
            gui.addGraphicalElement(new Oval(x, y, Color.RED, Color.ORANGE, 20));
            gui.addGraphicalElement(new Text(x, y - 10, Color.BLACK, Integer.toString(incendie.getVol_pour_eteindre())));
        }

        // Dessin des robots
        for (Robot robot : robots) {
            int x = robot.getPosition().getColonne() * 50 +25 ;
            int y = robot.getPosition().getLigne() * 50 +25;
            gui.addGraphicalElement(new Oval(x, y, Color.BLUE, Color.CYAN, 20));
            gui.addGraphicalElement(new Text(x, y + 10, Color.BLACK, robot.getType()));
        }
    }

    /**
     * Obtenir la couleur pour chaque type de terrain.
     */
    private String  obtenirCouleurNature(NatureTerrain nature) {
        switch (nature) {
            case FORET: return "tiles/FORET.png";
            case EAU: return "tiles/EAU.png";
            case ROCHE: return "tiles/ROCHE.png";
            case TERRAIN_LIBRE: return "tiles/TERRAIN_LIBRE.png";
            case HABITAT :return "tiles/HABITAT.png";
            default: return "tiles/TERRAIN_LIBRE.png";
        }
    }
}
