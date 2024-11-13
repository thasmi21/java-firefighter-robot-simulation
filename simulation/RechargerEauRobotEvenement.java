package simulation;

import robots.Robot;
import map.Carte;
import exceptions.RobotIllegalPosException;

public class RechargerEauRobotEvenement extends Evenement {
    
    private Robot robot;
    private Carte carte;
    
    public RechargerEauRobotEvenement(Robot robot, Carte carte, long date) {
        super(date);
        this.robot = robot;
        this.carte = carte;
    }

    public void execute() {
        try {
            this.robot.rechargerReservoir(carte);
        } catch(RobotIllegalPosException e) {
            System.out.println(e);
        }
    }

}
