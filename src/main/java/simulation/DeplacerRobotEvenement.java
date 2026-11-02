package simulation;
import robots.Robot;
import map.Case;

import java.util.Objects;

import exceptions.*;

public class DeplacerRobotEvenement extends Evenement {
    
    private Robot robot; // Référence du robot à déplacer
    private Case coord;  // Cordooneés de la case où déplacer le robot

    /** Construit un événement de déplacement <b>unitaire</b> du robot (déplacement vers <b>une seule case</b>) à une date donnée
     * @param robot Référence du robot à déplacer
     * @param coord Coordonées de la case d'arrivée du robot
     * @param date  Date du déplacement
    */
    public DeplacerRobotEvenement(Robot robot, Case coord, long date) {
        super(date);
        this.robot = robot;
        this.coord = coord;
    }

    /** Exécute le déplacement du robot <b>si la case souhaitée est accessible</b> */
    public void execute() {
        try {
            this.robot.setPosition(this.coord); // On essaie de déplacer le robot à la case voulue
        } catch(RobotIllegalPosException e) {
            System.out.println(e); // Sinon on affiche l'erreur
        }
    }

    @Override
    public String toString() {
        return "DeplacerRobotEvenement["+this.getDate()+"] concernant le robot en "+this.robot.getType()+" vers la position "+this.coord;
    }
    
    /** Génère un code de hashage pour une instance de la classe */
    @Override public int hashCode() {
        return Objects.hash(super.hashCode(), this.robot.hashCode(), this.coord.hashCode());
    }

}
