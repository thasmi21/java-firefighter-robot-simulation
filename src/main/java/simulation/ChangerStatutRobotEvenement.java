package simulation;
import robots.Robot;
import robots.Etat;

import java.util.Objects;

import map.Case;

public class ChangerStatutRobotEvenement extends Evenement {
    
    private Robot robot; // Robot dont l'état doit être modifié
    private Etat etat;   // Nouvel état du robot après modification
    private Case focus;  // Case de focus du robot
    private Simulateur sim;

    public ChangerStatutRobotEvenement(Robot robot, Etat etat, Case focus, long date,Simulateur sim){
        super(date);
        this.robot = robot;
        this.etat = etat;
        this.sim = sim;
        this.focus = focus;
    }   

    public void execute() {
        this.robot.setEtat(this.etat, this.focus);
        sim.getChefPompier().resolution();
    }

    @Override
    public String toString(){
        return "ChangerStatutRobotEvenement concernant le robot en "+this.robot.getType()+" pour l'état "+this.etat+" en focusant "+this.focus;
    }

    /** Génère un code de hashage pour une instance de la classe */
    @Override public int hashCode() {
        return Objects.hash(super.hashCode(), this.robot.hashCode(), this.etat.hashCode(), this.focus.hashCode(), this.sim.hashCode());
    }
}
