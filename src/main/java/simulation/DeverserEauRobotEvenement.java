package simulation;
import java.util.Objects;

import map.Incendie;
import robots.Robot;

public class DeverserEauRobotEvenement extends Evenement {
    
    private Robot robot;                  // Référence du robot dont on veut déverser l'eau
    private DonneesSimulation donneesSimulation;
    private Simulateur sim;
    /** Construit un événement de déversement de l'eau du réservoir du robot sur sa <b>case actuelle</b> à une date donnée
     * @param robot Référence du robot dont déverser l'eau
     * @param date  Date du déversement
    */
    public DeverserEauRobotEvenement(Robot robot, long date, DonneesSimulation donneesSimulation,Simulateur sim) {
        super(date);
        this.robot = robot;
        this.donneesSimulation = donneesSimulation;
        this.sim = sim;
    }

    /** Exécute le déplacement du robot <b>si la case souhaitée est accessible</b> */
    public void execute() {
        System.out.println(donneesSimulation.getIncendies().keySet());
        System.out.println(donneesSimulation.getIncendies());
        int volume_deverse = this.robot.deverserEau(); // On déverse l'eau du réservoir du robot sur sa case courante (potentiellement aucune eau ou poudre déversée)
        Incendie incendie = donneesSimulation.getIncendieByCase(this.robot.getPosition());
        // S'il y a bien un feu là où se situe le robot on modifie (potentiellement) le feu
        System.out.println(incendie);
        if(incendie != null) { 
            incendie.setFeuDec(volume_deverse);
            
            if(incendie.getVolExtinction() <= 0){
                donneesSimulation.removeIncendieByCase(incendie.getPosition());
            }
        }
        if(robot.getEtatReservoir() == 0){
            RechargerEauRobotEvenement eau = new RechargerEauRobotEvenement(robot,donneesSimulation.getWaterTiles(),donneesSimulation.getCarte(),sim,sim.getDateSimulation());
            sim.addEvenement(eau);
        }
    }

    @Override
    public String toString() {
        return "DeverserEauRobotEvenement["+this.getDate()+"] concernant le robot en "+this.robot.getType();
    }

    /** Génère un code de hashage pour une instance de la classe */
    @Override public int hashCode() {
        return Objects.hash(super.hashCode(), this.robot.hashCode(), this.donneesSimulation.hashCode(), this.sim.hashCode());
    }

}
