package simulation;
import map.Incendie;
import robots.Robot;

public class DeverserEauRobotEvenement extends Evenement {
    
    private Robot robot;                  // Référence du robot dont on veut déverser l'eau
    private DonneesSimulation simulation; // On utilise la référence d'une simulation pour pouvoir modifier les incendies au besoin

    /** Construit un événement de déversement de l'eau du réservoir du robot sur sa <b>case actuelle</b> à une date donnée
     * @param robot Référence du robot dont déverser l'eau
     * @param date  Date du déversement
    */
    public DeverserEauRobotEvenement(Robot robot, DonneesSimulation simulation, long date) {
        super(date);
        this.robot = robot;
        this.simulation = simulation;
    }

    /** Exécute le déplacement du robot <b>si la case souhaitée est accessible</b> */
    public void execute() {
        int volume_deverse = this.robot.deverserEau(); // On déverse l'eau du réservoir du robot sur sa case courante (potentiellement aucune eau ou poudre déversée)
        Incendie incendie = this.simulation.getIncendieByCase(this.robot.getPosition());
        incendie.setFeuDec(volume_deverse);
        if(incendie.getVolExtinction() <= 0){
            this.simulation.removeIncendieByCase(incendie.getPosition());
        }
    }

}
