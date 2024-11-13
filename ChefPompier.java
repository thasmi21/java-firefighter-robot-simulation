import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import map.Carte;
import map.Case;
import map.Chemin;
import map.Incendie;
import robots.Etat;
import robots.Robot;
import simulation.Evenement;
import simulation.Simulateur;

public class ChefPompier {
    private Carte carte;
    private ArrayList<Robot> robots;
    private HashMap<Case, Incendie> incendies;
    private PriorityQueue<Evenement> evenements;
    private Simulateur sim;

    public ChefPompier(Carte carte, HashMap<Case, Incendie> incendies, ArrayList<Robot> robots,PriorityQueue<Evenement> evenements, Simulateur sim) {
        this.carte = carte;
        this.robots = robots;
        this.incendies = incendies;
        this.evenements = evenements;
        this.sim =sim;
    }   

    public void resolution(){

        HashMap<Incendie, Integer> eteint = new HashMap<>(); //0 : feu 1: pris en charge 2:eteint

        for (Incendie incendie : incendies.values()) {
            eteint.put(incendie, 0); // chaque incendie est non pris en charge initialement
        }

        for (Incendie incendie : incendies.values()) {
            if (eteint.get(incendie) == 0) { // Vérifie si l'incendie n'a pas déjà été affecté
                for (Robot robot : robots) {
                    if (robot.getEtat() == Etat.LIBRE && robot.getEtatReservoir() > 0) {
                        Chemin chem = robot.dijkstra(carte, incendie.getPosition());
                        if (chem != null) {
                            eteint.put(incendie, 1);
                            robot.mkevent(incendie, chem,evenements,sim);
                            robot.setEtat(Etat.OCCUPE);
                            break;
                        }
                    }
                }
            }
        }
    }

}