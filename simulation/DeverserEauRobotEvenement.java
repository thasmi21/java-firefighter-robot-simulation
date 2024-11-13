package simulation;
import map.Incendie;
import robots.Robot;

public class DeverserEauRobotEvenement extends Evenement {
    
    private Robot robot;                  // Référence du robot dont on veut déverser l'eau

    /** Construit un événement de déversement de l'eau du réservoir du robot sur sa <b>case actuelle</b> à une date donnée
     * @param robot Référence du robot dont déverser l'eau
     * @param date  Date du déversement
    */
    public DeverserEauRobotEvenement(Robot robot, long date) {
        super(date);
        this.robot = robot;
    }

    /** Exécute le déplacement du robot <b>si la case souhaitée est accessible</b> */
    public void execute() {
        int volume_deverse = this.robot.deverserEau(); // On déverse l'eau du réservoir du robot sur sa case courante (potentiellement aucune eau ou poudre déversée)
        Incendie incendie = Evenement.getDonneesSimulation().getIncendieByCase(this.robot.getPosition());
        // S'il y a bien un feu là où se situe le robot on modifie (potentiellement) le feu
        if(incendie != null) { 
            incendie.setFeuDec(volume_deverse);
            if(incendie.getVolExtinction() <= 0){
                Evenement.getDonneesSimulation().removeIncendieByCase(incendie.getPosition());
            }
        }
    }

}
