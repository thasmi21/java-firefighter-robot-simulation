package simulation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
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
    private Simulateur sim;

    public ChefPompier(Simulateur sim) {
        this.sim =sim;
    }   

    public void resolution() {
        DonneesSimulation donnees_sim = sim.getDonneesSimulation();
        
        for(Incendie i : donnees_sim.getIncendies().values()) {
            boolean isThisFeuHandled = false;
            ArrayList<Robot> robots_sim = donnees_sim.getRobots();
            ArrayList<Integer> id_robots_occupes = new ArrayList<Integer>();

            for (int j = 0; j < robots_sim.size() && !isThisFeuHandled; j++) {
                if(robots_sim.get(j).getFocusArea() != null)
                    isThisFeuHandled = (robots_sim.get(j).getFocusArea().equals(i.getPosition()) && robots_sim.get(j).getEtat().equals(Etat.OCCUPE));
                if(robots_sim.get(j).getEtat().equals(Etat.LIBRE))
                    id_robots_occupes.add(j);
            }

            if(!isThisFeuHandled && id_robots_occupes.size() > 0) { // Si le feu n'est pas géré et qu'il y a des robots libres
                int id_robot_a_envoyer = id_robots_occupes.get(0);
                Chemin chemin_robot_a_envoyer = robots_sim.get(id_robot_a_envoyer).dijkstra(donnees_sim.getCarte(), i.getPosition());
                for(int j = 1; j < id_robots_occupes.size(); j++) {
                    Chemin buff_chemin = robots_sim.get(j).dijkstra(donnees_sim.getCarte(), i.getPosition());
                    if(buff_chemin.getTempsTrajet() < chemin_robot_a_envoyer.getTempsTrajet()) { // Si on a trouvé un trajet plus rapide avec un autre robot on préférera ce dernier
                        id_robot_a_envoyer = j;
                        chemin_robot_a_envoyer = buff_chemin;
                    }
                }

                robots_sim.get(id_robot_a_envoyer).mkevent(i, chemin_robot_a_envoyer, sim); // On envoie finalement le bon robot
            }

        }
    }

    @Override public String toString() {
        return "Je suis le robot pompier associé à la simulation "+this.sim;
    }

    /** Génère un code de hashage pour une instance de la classe */
    @Override public int hashCode() {
        return Objects.hash(this.sim.hashCode());
    }
    
}