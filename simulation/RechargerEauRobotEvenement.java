package simulation;

import robots.Robot;
import exceptions.RobotIllegalPosException;

public class RechargerEauRobotEvenement extends Evenement {
    
    private Robot robot;
    
    public RechargerEauRobotEvenement(Robot robot, long date) {
        super(date);
        this.robot = robot;
    }

    public void execute() {
        try {
            this.robot.rechargerReservoir();
        } catch(RobotIllegalPosException e) {
            System.out.println(e);
        }
    }

}
