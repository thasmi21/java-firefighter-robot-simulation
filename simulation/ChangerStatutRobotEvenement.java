package simulation;
import robots.Robot;
import robots.Etat;

public class ChangerStatutRobotEvenement extends Evenement {
    
    private Robot robot; // Robot dont l'état doit être modifié
    private Etat etat;   // Nouvel état du robot après modification

    public ChangerStatutRobotEvenement(Robot robot, Etat etat, long date){
        super(date);
        this.robot = robot;
        this.etat = etat;
    }   

    public void execute() {
        this.robot.setEtat(this.etat);
    }

}
