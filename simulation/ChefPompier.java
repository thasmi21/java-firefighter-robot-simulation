package simulation;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Map;
//import java.util.PriorityQueue;
import java.util.PriorityQueue;

import map.Carte;
import map.Case;
import map.Chemin;
import map.Incendie;
import robots.Etat;
import robots.Robot;

public class ChefPompier {
    private Carte carte;
    private ArrayList<Robot> robots;
    private HashMap<Case, Incendie> incendies;
    private Simulateur sim;

    public ChefPompier(Carte carte, HashMap<Case, Incendie> incendies, ArrayList<Robot> robots, Simulateur sim) {
        this.carte = carte;
        this.robots = robots;
        this.incendies = incendies;
        this.sim =sim;
    }   

    public void resolution() {
        PriorityQueue<Incendie> feu = new PriorityQueue<>();  

        for (Incendie incendie : incendies.values()) {
            feu.add(incendie);
        }


        for (Robot robot : robots) {
            System.out.println(robot.getEtat());
            if (feu.peek() != null){
                boolean incendieAffecte = false;
                    if (robot.getEtatReservoir() > 0 && robot.getEtat() == Etat.LIBRE) {
                        Incendie incendie = feu.poll();
                        Chemin chem = robot.dijkstra(carte, incendie.getPosition());
                        if (chem != null) {
                            incendieAffecte = true;
                            robot.setEtat(Etat.OCCUPE);
                            robot.mkevent(incendie, chem, sim);
                            
                        } else {
                            System.out.println("Aucun chemin trouvé pour le robot vers l'incendie à la position : " + incendie.getPosition());
                        }
                    if (!incendieAffecte) {
                    System.out.println("Aucun robot libre ou disponible pour l'incendie à la position : " + incendie.getPosition());
                }
            }
        }
    }
    
}
}