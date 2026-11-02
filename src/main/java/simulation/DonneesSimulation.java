package simulation;
import gui.GUISimulator;
import gui.ImageElement;
//import gui.Rectangle;
import gui.Text;
import map.Carte;
import map.Case;
import map.Incendie;
import map.NatureTerrain;
import robots.Robot;
import gui.Oval;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import exceptions.CarteHorsCarteException;

public class DonneesSimulation {
    private Carte carte;
    private ArrayList<Robot> robots;
    private HashMap<Case, Incendie> incendies;
    private ArrayList<Case> watertiles;

    public DonneesSimulation(Carte carte, HashMap<Case, Incendie> incendies, ArrayList<Robot> robots,ArrayList<Case> watertiles) {
        this.carte = carte;
        this.robots = robots;
        this.incendies = incendies;
        this.watertiles = remplirWaterTile();
    }       
    
    private ArrayList<Case> remplirWaterTile(){
        ArrayList<Case> waterTile = new ArrayList<>();
        for(int i = 0; i<carte.getNbColonnes(); i++){ 
            for(int k =0; k< carte.getNbLignes(); k++){
                try {
                    Case currentCase = carte.getCase(i, k);
                    if (currentCase.equalsEAU()) {
                        waterTile.add(currentCase);
                    }
                    } catch (Exception e) {}
            }
        }
        return waterTile;
    }

    /** Retourne l'ensemble des cases constituées d'eau de la simulation */
    public ArrayList<Case> getWaterTiles() {
        return this.watertiles;
    }

    /** Renvoie la carte courante de la simulation */
    public Carte getCarte() {
        return this.carte;
    }


    /** Renvoie l'ensemble des robots de la simulation */
    public ArrayList<Robot> getRobots() {
        return this.robots;
    }

    /** Renvoie l'ensemble des incendies de la carte sous la forme d'une HashMap où les clées sont les cases et les valeurs les incendies */
    public HashMap<Case, Incendie> getIncendies() {
        return this.incendies;
    }

    /** Renvoie l'incendie à la case c (ou null si il n'y a pas d'incendie à cette case) 
     * @param c case où se trouve potentiellement l'incendie à récupérer
    */
    public Incendie getIncendieByCase(Case c) {
        return this.incendies.get(c);
    }

    /** Permet de retirer un incendie de la simulation */
    public void removeIncendieByCase(Case c){
        this.incendies.remove(c);
    }

    /** Permet d'ajouter un incendie à la simulation */
    public void putIncendie(Incendie incendie){
        this.incendies.put(incendie.getPosition(), incendie);
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

                gui.addGraphicalElement(new ImageElement(j * 30 , i * 30,image,30,30,null));
                }
                catch(CarteHorsCarteException e){
                    System.err.println("erreur depassement création care donneesimulation");
                }
            }
        }

        // Dessin des incendies
        for(Case c : incendies.keySet()){
            Incendie i = incendies.get(c);
            int x = i.getPosition().getColonne() * 30 +15 ;
            int y = i.getPosition().getLigne() * 30 +15 ;
            gui.addGraphicalElement(new Oval(x, y, Color.RED, Color.ORANGE, 20));
            gui.addGraphicalElement(new Text(x, y - 10, Color.BLACK, Integer.toString(i.getVolExtinction())));
        }

        // Dessin des robots
        for (Robot robot : robots) {
            int x = robot.getPosition().getColonne() * 30 +15 ;
            int y = robot.getPosition().getLigne() * 30 +15;
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

    @Override public String toString() {
        return "Données de simulation contenant 1 carte, "+this.robots.size()+" robots, "+this.incendies.size()+" incendies et "+this.watertiles.size()+" cases d'eau.";
    }

    /** Génère un code de hashage pour une instance de la classe */
    @Override public int hashCode() {
        return Objects.hash(this.carte.hashCode(), this.robots.hashCode(), this.incendies.hashCode(), this.watertiles.hashCode());
    }
}
