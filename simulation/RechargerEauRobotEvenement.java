package simulation;

import robots.Robot;
import java.util.ArrayList;

import exceptions.RobotIllegalPosException;
import map.*;


public class RechargerEauRobotEvenement extends Evenement {
    
    private Robot robot;
    private ArrayList<Case> watertiles;
    private Carte carte;
    private Simulateur sim;
    
    public RechargerEauRobotEvenement(Robot robot, ArrayList<Case> watertiles,Carte carte,Simulateur sim,long date) {
        super(date);
        this.robot = robot;
        this.watertiles = watertiles;
        this.carte = carte;
        this.sim = sim;
    }

    public void execute() {
        try {
            this.robot.rechargerReservoir(watertiles,carte,sim);
            sim.getChefPompier().resolution();
        } catch (RobotIllegalPosException e) {}
    }
 
}
