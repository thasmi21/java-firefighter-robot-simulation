package simulation;
import robots.Robot;
import robots.Etat;

public class ChangerStatutRobotEvenement extends Evenement {
    
    private Robot robot; // Robot dont l'état doit être modifié
    private Etat etat;   // Nouvel état du robot après modification
    private Simulateur sim;

    public ChangerStatutRobotEvenement(Robot robot, Etat etat, long date,Simulateur sim){
        super(date);
        this.robot = robot;
        this.etat = etat;
        this.sim = sim;
    }   

    public void execute() {
        this.robot.setEtat(this.etat);
        sim.getChefPompier().resolution();
    }

}
