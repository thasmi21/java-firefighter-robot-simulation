package simulation;

import robots.Robot;
import java.util.ArrayList;
import java.util.Objects;

import exceptions.RobotIllegalPosException;
import map.*;


public class RechargerEauRobotEvenement extends Evenement {
    
    private Robot robot;                // Robot que l'on veut recharger
    private ArrayList<Case> watertiles; // Ensemble des cases d'eau de la carte
    private Carte carte;                // Carte courante de la simulation
    private Simulateur sim;             // Simulateur courant de la simulation
    
    public RechargerEauRobotEvenement(Robot robot, ArrayList<Case> watertiles,Carte carte,Simulateur sim,long date) {
        super(date);
        this.robot = robot;
        this.watertiles = watertiles;
        this.carte = carte;
        this.sim = sim;
    }

    /** Méthode exécutant la recharge du réservoir du robot et ordonnant au chef pompier de recalculer la solution */
    public void execute() {
        try {
            this.robot.rechargerReservoir(watertiles,carte,sim);
            sim.getChefPompier().resolution();
        } catch (RobotIllegalPosException e) {}
    }

    @Override
    public String toString() {
        return "RechargerEauRobotEvenement["+this.getDate()+"] concernant le robot en "+this.robot.getType();
    }

    /** Génère un code de hashage pour une instance de la classe */
    @Override public int hashCode() {
        return Objects.hash(super.hashCode(), this.robot.hashCode(), this.watertiles.hashCode(), this.carte.hashCode(), this.sim.hashCode());
    }
 
}
