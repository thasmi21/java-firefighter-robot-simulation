package simulation;
import robots.Robot;
import robots.Etat;
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

}
