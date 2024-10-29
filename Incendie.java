public class Incendie {
    Case position;
    int vol_pour_eteindre;

    public Incendie(Case position, int vol_pour_eteindre){
        this.position = position;
        this.vol_pour_eteindre = vol_pour_eteindre;
    }

    /** methode pour obtenir la position de l'incendie*/
    public Case getPosition() {
        return position;
    }

    /** methode pour obtenir la quantité vol_pour_eteindre*/
    public int getVol_pour_eteindre() {
        return vol_pour_eteindre;
    }

    /** methode pour deduir la quantité vol de la quantité vol_pour_eteindre
     * @param vol : volume a déduire du vol necessaire pour eteindre le feu*/
    public int setFeuDec(int vol){
        return this.vol_pour_eteindre - vol;
    }

}
