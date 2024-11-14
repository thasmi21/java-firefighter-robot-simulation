import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Map;
//import java.util.PriorityQueue;

import map.Carte;
import map.Case;
import map.Chemin;
import map.Incendie;
import robots.Etat;
import robots.Robot;
//import simulation.Evenement;
import simulation.Simulateur;

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
        HashMap<Incendie, Integer> eteint = new HashMap<>(); // 0 : feu, 1 : pris en charge, 2 : éteint
    
        // Initialisation de l'état de chaque incendie
        for (Incendie incendie : incendies.values()) {
            eteint.put(incendie, 0);
        }
    
        for (Incendie incendie : incendies.values()) {
            // Vérifie si l'incendie est déjà pris en charge ou éteint
            if (eteint.get(incendie) == 0) {
                boolean incendieAffecte = false;
    
                for (Robot robot : robots) {
                    if (robot.getEtatReservoir() > 0) {
                        Chemin chem = robot.dijkstra(carte, incendie.getPosition());
    
                        if (chem != null) {
                            // Marque l'incendie comme pris en charge
                            System.out.println(chem);
                            eteint.put(incendie, 1);
                            incendieAffecte = true;
                            
                            System.out.println("Robot affecté à l'incendie à la position : " + incendie.getPosition());
                            
                            // Planifie l'intervention du robot
                            robot.mkevent(incendie, chem, sim);
                            robot.setEtat(Etat.OCCUPE);
                            
                            break;
                        } else {
                            System.out.println("Aucun chemin trouvé pour le robot vers l'incendie à la position : " + incendie.getPosition());
                        }
                    }
                }
    
                // Si aucun robot n'a pu être affecté à cet incendie
                if (!incendieAffecte) {
                    System.out.println("Aucun robot libre ou disponible pour l'incendie à la position : " + incendie.getPosition());
                }
            }
        }
    }
    

}