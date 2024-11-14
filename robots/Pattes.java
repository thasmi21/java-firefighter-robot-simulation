package robots;
import exceptions.RobotIllegalPosException;
import map.Carte;
import map.Case;
import map.NatureTerrain;

public class Pattes extends Robot{

    public Pattes(Case position){
        super("Patte",Etat.LIBRE,41.6, 8.33, Integer.MAX_VALUE, 0, 10, 1, Integer.MAX_VALUE, position, new NatureTerrain[] {NatureTerrain.TERRAIN_LIBRE, NatureTerrain.ROCHE, NatureTerrain.FORET, NatureTerrain.HABITAT});
    }

    public double getVitesse(NatureTerrain actuelle){
        switch (actuelle) {
            case NatureTerrain.EAU:
                return 0.0;
            case NatureTerrain.ROCHE: 
                return getVitesseBase()/3;
            default:
                return getVitesseBase();
        }
    }

    @Override // On réécrit la méthode derverserEau pour le robot à pattes uniquement car son réservoir est le seul à être infini
    public int deverserEau() {
        // Il n'y a pas d'actualisation de l'état du réservoir à faire car il est considéré de taille infinie
        return this.getDechargeReservoir(); // On se contente donc de renvoyer le volume de poudre déversé
    }
}