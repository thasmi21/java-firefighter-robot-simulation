public interface Simulable {
    /**
     * Passe à l'état suivant de la simulation.
     */
    void next();

    /**
     * Redémarre la simulation depuis l'état initial.
     */
    void restart();
}
